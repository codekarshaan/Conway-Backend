package com.conway.backend.notification.service;

import com.conway.backend.admin.entity.Admin;
import com.conway.backend.notification.dto.response.NotificationResponse;

import java.util.List;

public interface NotificationService {

    void createNotification(
            Admin admin,
            String type,
            String title,
            String message,
            String referenceType,
            Long referenceId
    );

    void createNotificationForAllActiveAdmins(
            String type,
            String title,
            String message,
            String referenceType,
            Long referenceId
    );

    List<NotificationResponse>
    getMyNotifications();

    long getUnreadCount();

    void markAsRead(
            Long notificationId
    );

    void markAllAsRead();
}