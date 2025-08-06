package com.kodkahvesi.resumeweb.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity representing the "about me" content stored in the database.
 */
@Entity
@Table(name = "about_me")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AboutMe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String content;
}