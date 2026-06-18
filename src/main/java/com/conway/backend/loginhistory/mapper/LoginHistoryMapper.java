package com.conway.backend.loginhistory.mapper;

import com.conway.backend.loginhistory.dto.response.LoginHistoryResponse;
import com.conway.backend.loginhistory.entity.LoginHistory;

public class LoginHistoryMapper {

    private LoginHistoryMapper() {
    }

    public static LoginHistoryResponse toResponse(
            LoginHistory loginHistory
    ) {

        return LoginHistoryResponse.builder()
                .id(loginHistory.getId())
                .adminId(
                        loginHistory.getAdmin().getId()
                )
                .adminName(
                        loginHistory.getAdmin().getFullName()
                )
                .email(
                        loginHistory.getAdmin().getEmail()
                )
                .loginTime(
                        loginHistory.getLoginTime()
                )
                .ipAddress(
                        loginHistory.getIpAddress()
                )
                .userAgent(
                        loginHistory.getUserAgent()
                )
                .build();
    }
}