package com.conway.backend.master.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "city_name",
            nullable = false,
            unique = true
    )
    private String cityName;

    @Column(
            name = "is_active",
            nullable = false
    )
    private Boolean isActive;
}