package com.conway.backend.notification.service.impl;

import com.conway.backend.admin.entity.Admin;
import com.conway.backend.admin.repository.AdminRepository;
import com.conway.backend.exception.ForbiddenException;
import com.conway.backend.exception.ResourceNotFoundException;
import com.conway.backend.notification.dto.response.NotificationResponse;
import com.conway.backend.notification.entity.Notification;
import com.conway.backend.notification.mapper.NotificationMapper;
import com.conway.backend.notification.repository.NotificationRepository;
import com.conway.backend.notification.service.NotificationService;
import com.conway.backend.security.userdetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl
        implements NotificationService {

    private final NotificationRepository
            notificationRepository;

    private final AdminRepository
            adminRepository;

    @Override
    @Transactional
    public void createNotification(
            Admin admin,
            String type,
            String title,
            String message,
            String referenceType,
            Long referenceId
    ) {

        Notification notification =
                Notification.builder()
                        .admin(admin)
                        .type(type)
                        .title(title)
                        .message(message)
                        .referenceType(referenceType)
                        .referenceId(referenceId)
                        .isRead(false)
                        .createdAt(LocalDateTime.now())
                        .build();

        notificationRepository.save(
                notification
        );
    }

    @Override
    @Transactional
    public void createNotificationForAllActiveAdmins(
            String type,
            String title,
            String message,
            String referenceType,
            Long referenceId
    ) {

        List<Admin> admins =
                adminRepository.findByIsActiveTrue();

        for (Admin admin : admins) {

            createNotification(
                    admin,
                    type,
                    title,
                    message,
                    referenceType,
                    referenceId
            );
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationResponse>
    getMyNotifications() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        CustomUserDetails currentUser =
                (CustomUserDetails)
                        authentication.getPrincipal();

        Long adminId =
                currentUser.getAdmin().getId();

        return notificationRepository
                .findByAdminIdOrderByCreatedAtDesc(
                        adminId
                )
                .stream()
                .map(NotificationMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public long getUnreadCount() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        CustomUserDetails currentUser =
                (CustomUserDetails)
                        authentication.getPrincipal();

        Long adminId =
                currentUser.getAdmin().getId();

        return notificationRepository
                .countByAdminIdAndIsReadFalse(
                        adminId
                );
    }

    @Override
    @Transactional
    public void markAsRead(
            Long notificationId
    ) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        CustomUserDetails currentUser =
                (CustomUserDetails)
                        authentication.getPrincipal();

        Long adminId =
                currentUser.getAdmin().getId();

        Notification notification =
                notificationRepository
                        .findById(notificationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Notification not found"
                                )
                        );

        if (
                !notification.getAdmin()
                        .getId()
                        .equals(adminId)
        ) {

            throw new ForbiddenException(
                    "You are not allowed to access this notification"
            );
        }

        notification.setIsRead(true);

        notificationRepository.save(
                notification
        );
    }

    @Override
    @Transactional
    public void markAllAsRead() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        CustomUserDetails currentUser =
                (CustomUserDetails)
                        authentication.getPrincipal();

        Long adminId =
                currentUser.getAdmin().getId();

        List<Notification> notifications =
                notificationRepository
                        .findByAdminIdAndIsReadFalse(
                                adminId
                        );

        notifications.forEach(
                notification ->
                        notification.setIsRead(true)
        );

        notificationRepository.saveAll(
                notifications
        );
    }
}