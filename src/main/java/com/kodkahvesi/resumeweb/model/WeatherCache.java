package com.kodkahvesi.resumeweb.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Entity caching weather data for a particular city to reduce API calls.
 */
@Entity
@Table(name = "weather_cache")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherCache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private Float temperature;

    private String description;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}