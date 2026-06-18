package com.conway.backend.notification.controller;

import com.conway.backend.notification.dto.response.NotificationResponse;
import com.conway.backend.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService
            notificationService;

    @GetMapping
    public List<NotificationResponse>
    getMyNotifications() {

        return notificationService
                .getMyNotifications();
    }

    @GetMapping("/unread-count")
    public long getUnreadCount() {

        return notificationService
                .getUnreadCount();
    }

    @PatchMapping("/{id}/read")
    public void markAsRead(
            @PathVariable Long id
    ) {

        notificationService.markAsRead(
                id
        );
    }

    @PatchMapping("/read-all")
    public void markAllAsRead() {

        notificationService.markAllAsRead();
    }
}