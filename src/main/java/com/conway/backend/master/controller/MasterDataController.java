package com.conway.backend.master.controller;

import com.conway.backend.master.dto.response.CargoTypeResponse;
import com.conway.backend.master.dto.response.CityResponse;
import com.conway.backend.master.dto.response.TruckTypeResponse;
import com.conway.backend.master.service.MasterDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/master")
@RequiredArgsConstructor
public class MasterDataController {

    private final MasterDataService
            masterDataService;

    @GetMapping("/cities")
    public List<CityResponse> getCities() {

        return masterDataService.getCities();
    }

    @GetMapping("/truck-types")
    public List<TruckTypeResponse>
    getTruckTypes() {

        return masterDataService.getTruckTypes();
    }

    @GetMapping("/cargo-types")
    public List<CargoTypeResponse>
    getCargoTypes() {

        return masterDataService.getCargoTypes();
    }
}