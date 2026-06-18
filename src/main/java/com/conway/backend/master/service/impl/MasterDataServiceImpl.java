package com.conway.backend.master.service.impl;

import com.conway.backend.master.dto.response.CargoTypeResponse;
import com.conway.backend.master.dto.response.CityResponse;
import com.conway.backend.master.dto.response.TruckTypeResponse;
import com.conway.backend.master.repository.CargoTypeRepository;
import com.conway.backend.master.repository.CityRepository;
import com.conway.backend.master.repository.TruckTypeRepository;
import com.conway.backend.master.service.MasterDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MasterDataServiceImpl
        implements MasterDataService {

    private final CityRepository cityRepository;

    private final TruckTypeRepository truckTypeRepository;

    private final CargoTypeRepository cargoTypeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CityResponse> getCities() {

        return cityRepository.findAll()
                .stream()
                .map(city ->
                        CityResponse.builder()
                                .id(city.getId())
                                .cityName(city.getCityName())
                                .build()
                )
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TruckTypeResponse> getTruckTypes() {

        return truckTypeRepository.findAll()
                .stream()
                .map(truck ->
                        TruckTypeResponse.builder()
                                .id(truck.getId())
                                .truckName(
                                        truck.getTruckName()
                                )
                                .build()
                )
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CargoTypeResponse> getCargoTypes() {

        return cargoTypeRepository.findAll()
                .stream()
                .map(cargo ->
                        CargoTypeResponse.builder()
                                .id(cargo.getId())
                                .cargoName(
                                        cargo.getCargoName()
                                )
                                .build()
                )
                .toList();
    }
}