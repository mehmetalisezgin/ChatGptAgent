package com.kodkahvesi.resumeweb.repository;

import com.kodkahvesi.resumeweb.model.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for {@link ContactMessage} entities.
 */
@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
}