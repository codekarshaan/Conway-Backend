package com.conway.backend.enquiry.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateEnquiryStatusRequest {

    @NotNull
    private String status;
}