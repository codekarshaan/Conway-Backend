package com.conway.backend.master.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "truck_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TruckType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "truck_name",
            nullable = false,
            unique = true
    )
    private String truckName;

    @Column(
            name = "is_active",
            nullable = false
    )
    private Boolean isActive;
}