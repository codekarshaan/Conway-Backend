package com.conway.backend.master.service;

import com.conway.backend.master.dto.response.CargoTypeResponse;
import com.conway.backend.master.dto.response.CityResponse;
import com.conway.backend.master.dto.response.TruckTypeResponse;

import java.util.List;

public interface MasterDataService {

    List<CityResponse> getCities();

    List<TruckTypeResponse> getTruckTypes();

    List<CargoTypeResponse> getCargoTypes();
}