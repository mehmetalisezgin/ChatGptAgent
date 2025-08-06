package com.kodkahvesi.resumeweb.repository;

import com.kodkahvesi.resumeweb.model.WeatherCache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for {@link WeatherCache} entities.
 */
@Repository
public interface WeatherCacheRepository extends JpaRepository<WeatherCache, Long> {
    Optional<WeatherCache> findByCity(String city);
}