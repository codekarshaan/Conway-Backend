package com.conway.backend.enquirynote.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EnquiryNoteResponse {

    private Long id;

    private Long enquiryId;

    private Long adminId;

    private String adminName;

    private String note;

    private LocalDateTime createdAt;
}