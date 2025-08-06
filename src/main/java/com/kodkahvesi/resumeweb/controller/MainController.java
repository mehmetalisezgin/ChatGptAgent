package com.kodkahvesi.resumeweb.controller;

import com.kodkahvesi.resumeweb.dto.WeatherDTO;
import com.kodkahvesi.resumeweb.service.DataService;
import com.kodkahvesi.resumeweb.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Controller responsible for rendering the static pages such as home, about,
 * experiences, projects and tools. It fetches the necessary data from
 * underlying services and exposes them to Thymeleaf templates.
 */
@Controller
public class MainController {

    private final DataService dataService;
    private final WeatherService weatherService;

    @Autowired
    public MainController(DataService dataService, WeatherService weatherService) {
        this.dataService = dataService;
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<String> cities = Arrays.asList("Istanbul", "Ankara", "Izmir");
        Map<String, WeatherDTO> weather = weatherService.getWeatherForCities(cities);
        model.addAttribute("weather", weather);
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("about", dataService.getAboutContent());
        return "about";
    }

    @GetMapping("/experiences")
    public String experiences(Model model) {
        model.addAttribute("experiences", dataService.getExperiences());
        return "experiences";
    }

    @GetMapping("/projects")
    public String projects(Model model) {
        model.addAttribute("projects", dataService.getProjects());
        return "projects";
    }

    @GetMapping("/tools")
    public String tools(Model model) {
        model.addAttribute("tools", dataService.getTools());
        return "tools";
    }
}