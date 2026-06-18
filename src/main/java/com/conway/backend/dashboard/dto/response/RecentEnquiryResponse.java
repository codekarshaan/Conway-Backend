package com.conway.backend.dashboard.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RecentEnquiryResponse {

    private Long id;

    private String customerName;

    private String phoneNumber;

    private String status;

    private LocalDateTime createdAt;
}