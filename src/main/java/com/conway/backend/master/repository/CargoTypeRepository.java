package com.conway.backend.master.repository;

import com.conway.backend.master.entity.CargoType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoTypeRepository
        extends JpaRepository<CargoType, Long> {
}