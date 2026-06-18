package com.conway.backend.master.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TruckTypeResponse {

    private Long id;

    private String truckName;
}