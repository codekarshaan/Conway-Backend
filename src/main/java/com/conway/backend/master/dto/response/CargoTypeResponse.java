package com.conway.backend.master.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CargoTypeResponse {

    private Long id;

    private String cargoName;
}