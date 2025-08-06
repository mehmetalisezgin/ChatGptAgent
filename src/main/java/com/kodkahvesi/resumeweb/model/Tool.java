package com.kodkahvesi.resumeweb.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity representing a tool or technology skill.
 */
@Entity
@Table(name = "tools")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tool_name")
    private String toolName;
}