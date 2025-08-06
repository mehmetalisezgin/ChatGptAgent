package com.kodkahvesi.resumeweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Simple DTO carrying temperature and description for a city's weather.
 */
@Data
@AllArgsConstructor
public class WeatherDTO {
    private float temperature;
    private String description;
}