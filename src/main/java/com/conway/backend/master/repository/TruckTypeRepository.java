package com.conway.backend.master.repository;

import com.conway.backend.master.entity.TruckType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TruckTypeRepository
        extends JpaRepository<TruckType, Long> {
}