package com.kodkahvesi.resumeweb.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity representing a project that will be displayed on the projects page.
 */
@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "github_link")
    private String githubLink;
}