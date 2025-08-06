package com.kodkahvesi.resumeweb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Entity representing a contact message submitted through the contact form.
 */
@Entity
@Table(name = "contact_messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "İsim alanı boş bırakılamaz")
    private String name;

    @NotBlank(message = "E‑posta alanı boş bırakılamaz")
    @Email(message = "Geçerli bir e‑posta adresi giriniz")
    private String email;

    @NotBlank(message = "Mesaj alanı boş bırakılamaz")
    @Column(columnDefinition = "TEXT")
    private String message;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}