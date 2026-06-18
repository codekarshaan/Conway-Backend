package com.conway.backend.loginhistory.repository;

import com.conway.backend.loginhistory.entity.LoginHistory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginHistoryRepository
        extends JpaRepository<LoginHistory, Long> {

    @EntityGraph(attributePaths = {"admin"})
    List<LoginHistory>
    findAllByOrderByLoginTimeDesc();
}