package com.kodkahvesi.resumeweb.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity representing a professional experience entry.
 */
@Entity
@Table(name = "experiences")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "position")
    private String position;

    @Column(name = "year_start")
    private Integer yearStart;

    @Column(name = "year_end")
    private Integer yearEnd;
}