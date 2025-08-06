package com.kodkahvesi.resumeweb.repository;

import com.kodkahvesi.resumeweb.model.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for {@link Tool} entities.
 */
@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {
}