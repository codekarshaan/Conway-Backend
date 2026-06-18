package com.conway.backend.notification.mapper;

import com.conway.backend.notification.dto.response.NotificationResponse;
import com.conway.backend.notification.entity.Notification;

public class NotificationMapper {

    private NotificationMapper() {
    }

    public static NotificationResponse toResponse(
            Notification notification
    ) {

        return NotificationResponse.builder()
                .id(notification.getId())
                .type(notification.getType())
                .title(notification.getTitle())
                .message(notification.getMessage())
                .referenceType(
                        notification.getReferenceType()
                )
                .referenceId(
                        notification.getReferenceId()
                )
                .isRead(
                        notification.getIsRead()
                )
                .createdAt(
                        notification.getCreatedAt()
                )
                .build();
    }
}