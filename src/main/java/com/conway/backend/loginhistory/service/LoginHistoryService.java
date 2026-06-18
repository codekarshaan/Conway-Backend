package com.conway.backend.loginhistory.service;

import com.conway.backend.admin.entity.Admin;
import com.conway.backend.loginhistory.dto.response.LoginHistoryResponse;

import java.util.List;

public interface LoginHistoryService {

    void recordLogin(
            Admin admin,
            String ipAddress,
            String userAgent
    );

    List<LoginHistoryResponse>
    getLoginHistory();
}