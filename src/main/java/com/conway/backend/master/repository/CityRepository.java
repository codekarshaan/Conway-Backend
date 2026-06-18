package com.conway.backend.master.repository;

import com.conway.backend.master.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository
        extends JpaRepository<City, Long> {
}