package com.kodkahvesi.resumeweb.repository;

import com.kodkahvesi.resumeweb.model.AboutMe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for {@link AboutMe} entities.
 */
@Repository
public interface AboutMeRepository extends JpaRepository<AboutMe, Long> {
}