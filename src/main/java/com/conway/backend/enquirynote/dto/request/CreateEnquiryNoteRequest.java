package com.conway.backend.enquirynote.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateEnquiryNoteRequest {

    @NotBlank
    private String note;
}