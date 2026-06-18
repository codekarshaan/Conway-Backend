package com.conway.backend.enquiry.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EnquiryResponse {

    private Long id;

    private String customerName;

    private String phoneNumber;

    private String email;

    private String city;

    private String truckType;

    private String cargoType;

    private String message;

    private String status;

    private Long assignedAdminId;

    private LocalDateTime createdAt;
}