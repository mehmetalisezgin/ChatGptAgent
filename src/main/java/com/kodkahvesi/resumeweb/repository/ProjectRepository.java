package com.kodkahvesi.resumeweb.repository;

import com.kodkahvesi.resumeweb.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for {@link Project} entities.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}