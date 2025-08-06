package com.kodkahvesi.resumeweb.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodkahvesi.resumeweb.dto.WeatherDTO;
import com.kodkahvesi.resumeweb.model.WeatherCache;
import com.kodkahvesi.resumeweb.repository.WeatherCacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Service responsible for retrieving and caching weather data. It fetches
 * current weather information from a configured API for the requested cities
 * and caches the results to limit external API calls. If an API key is not
 * provided the service falls back to any cached values; if no cache exists
 * it returns a default placeholder.
 */
@Service
public class WeatherService {

    private final WeatherCacheRepository weatherCacheRepository;

    @Value("${weather.api.url:https://api.openweathermap.org/data/2.5/weather}")
    private String apiUrl;

    @Value("${weather.api.key:}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public WeatherService(WeatherCacheRepository weatherCacheRepository) {
        this.weatherCacheRepository = weatherCacheRepository;
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Returns a map of city names to WeatherDTO objects. If a cached value is
     * available and was updated within the last 30 minutes the cache is used.
     * Otherwise the weather API is queried. If the API key is missing the
     * service will return cached values or placeholder data.
     *
     * @param cities list of city names to look up
     * @return map of city to weather information
     */
    public Map<String, WeatherDTO> getWeatherForCities(List<String> cities) {
        Map<String, WeatherDTO> result = new LinkedHashMap<>();
        for (String city : cities) {
            // try to find cached entry
            Optional<WeatherCache> optCache = weatherCacheRepository.findByCity(city);
            WeatherCache cache = optCache.orElse(null);
            boolean useCache = false;
            if (cache != null && cache.getUpdatedAt() != null) {
                // if cache is fresh (updated within last 30 minutes), use it
                if (cache.getUpdatedAt().isAfter(LocalDateTime.now().minusMinutes(30))) {
                    useCache = true;
                }
            }
            if (useCache) {
                result.put(city, new WeatherDTO(cache.getTemperature(), cache.getDescription()));
                continue;
            }
            // if API key missing, fall back to cached or default
            if (apiKey == null || apiKey.isBlank()) {
                float temp = cache != null && cache.getTemperature() != null ? cache.getTemperature() : 0f;
                String description = cache != null ? cache.getDescription() : "Bilinmiyor";
                result.put(city, new WeatherDTO(temp, description));
                continue;
            }
            try {
                String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
                String url = apiUrl + "?q=" + encodedCity + "&units=metric&appid=" + apiKey;
                String json = restTemplate.getForObject(url, String.class);
                JsonNode node = objectMapper.readTree(json);
                float temperature = (float) node.path("main").path("temp").asDouble();
                String description = node.path("weather").get(0).path("description").asText();
                // update cache
                if (cache == null) {
                    cache = WeatherCache.builder()
                            .city(city)
                            .temperature(temperature)
                            .description(description)
                            .build();
                } else {
                    cache.setTemperature(temperature);
                    cache.setDescription(description);
                }
                weatherCacheRepository.save(cache);
                result.put(city, new WeatherDTO(temperature, description));
            } catch (Exception e) {
                // fallback to cache if available otherwise default
                float temp = cache != null && cache.getTemperature() != null ? cache.getTemperature() : 0f;
                String description = cache != null ? cache.getDescription() : "Bilinmiyor";
                result.put(city, new WeatherDTO(temp, description));
            }
        }
        return result;
    }
}