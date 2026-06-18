package com.conway.backend.notification.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificationResponse {

    private Long id;

    private String type;

    private String title;

    private String message;

    private String referenceType;

    private Long referenceId;

    private Boolean isRead;

    private LocalDateTime createdAt;
}