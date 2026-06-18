package com.conway.backend.enquiry.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateEnquiryRequest {

    @NotBlank
    private String customerName;

    @NotBlank
    private String phoneNumber;

    @Email
    private String email;

    @NotNull
    private Long cityId;

    @NotNull
    private Long truckTypeId;

    @NotNull
    private Long cargoTypeId;

    private String message;
}