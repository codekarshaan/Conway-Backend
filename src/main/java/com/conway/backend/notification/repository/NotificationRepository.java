package com.conway.backend.notification.repository;

import com.conway.backend.notification.entity.Notification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository
        extends JpaRepository<Notification, Long> {

    @EntityGraph(attributePaths = {"admin"})
    List<Notification>
    findByAdminIdOrderByCreatedAtDesc(
            Long adminId
    );

    @EntityGraph(attributePaths = {"admin"})
    List<Notification>
    findAllByOrderByCreatedAtDesc();

    long countByAdminIdAndIsReadFalse(
            Long adminId
    );

    List<Notification>
    findByAdminIdAndIsReadFalse(
            Long adminId
    );
    @EntityGraph(attributePaths = {"admin"})
    Optional<Notification>
    findByIdAndAdminId(
            Long notificationId,
            Long adminId
    );

}