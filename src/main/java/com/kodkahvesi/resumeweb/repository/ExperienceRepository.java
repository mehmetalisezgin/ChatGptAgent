package com.kodkahvesi.resumeweb.repository;

import com.kodkahvesi.resumeweb.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for {@link Experience} entities.
 */
@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}