package com.conway.backend.loginhistory.service.impl;

import com.conway.backend.admin.entity.Admin;
import com.conway.backend.loginhistory.dto.response.LoginHistoryResponse;
import com.conway.backend.loginhistory.entity.LoginHistory;
import com.conway.backend.loginhistory.mapper.LoginHistoryMapper;
import com.conway.backend.loginhistory.repository.LoginHistoryRepository;
import com.conway.backend.loginhistory.service.LoginHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginHistoryServiceImpl
        implements LoginHistoryService {

    private final LoginHistoryRepository
            loginHistoryRepository;

    @Override
    @Transactional
    public void recordLogin(
            Admin admin,
            String ipAddress,
            String userAgent
    ) {

        LoginHistory loginHistory =
                LoginHistory.builder()
                        .admin(admin)
                        .loginTime(
                                LocalDateTime.now()
                        )
                        .ipAddress(ipAddress)
                        .userAgent(userAgent)
                        .build();

        loginHistoryRepository.save(
                loginHistory
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<LoginHistoryResponse>
    getLoginHistory() {

        return loginHistoryRepository
                .findAllByOrderByLoginTimeDesc()
                .stream()
                .map(LoginHistoryMapper::toResponse)
                .toList();
    }
}