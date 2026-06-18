package com.conway.backend.master.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cargo_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CargoType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "cargo_name",
            nullable = false,
            unique = true
    )
    private String cargoName;

    @Column(
            name = "is_active",
            nullable = false
    )
    private Boolean isActive;
}