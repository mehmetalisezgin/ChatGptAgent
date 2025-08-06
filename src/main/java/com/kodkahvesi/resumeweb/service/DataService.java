package com.kodkahvesi.resumeweb.service;

import com.kodkahvesi.resumeweb.model.*;
import com.kodkahvesi.resumeweb.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service component for accessing persisted resume data.
 */
@Service
public class DataService {

    private final AboutMeRepository aboutMeRepository;
    private final ExperienceRepository experienceRepository;
    private final ProjectRepository projectRepository;
    private final ToolRepository toolRepository;

    @Autowired
    public DataService(AboutMeRepository aboutMeRepository,
                       ExperienceRepository experienceRepository,
                       ProjectRepository projectRepository,
                       ToolRepository toolRepository) {
        this.aboutMeRepository = aboutMeRepository;
        this.experienceRepository = experienceRepository;
        this.projectRepository = projectRepository;
        this.toolRepository = toolRepository;
    }

    /**
     * Returns the about me content. If none exists, returns an empty string.
     */
    public String getAboutContent() {
        return aboutMeRepository.findAll().stream()
                .findFirst()
                .map(AboutMe::getContent)
                .orElse("");
    }

    /**
     * Returns all experiences ordered by start year descending.
     */
    public List<Experience> getExperiences() {
        return experienceRepository.findAll()
                .stream()
                .sorted((a, b) -> Integer.compare(b.getYearStart(), a.getYearStart()))
                .toList();
    }

    /**
     * Returns all projects.
     */
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    /**
     * Returns all tools.
     */
    public List<Tool> getTools() {
        return toolRepository.findAll();
    }
}