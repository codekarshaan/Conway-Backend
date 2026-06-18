package com.conway.backend.loginhistory.controller;

import com.conway.backend.loginhistory.dto.response.LoginHistoryResponse;
import com.conway.backend.loginhistory.service.LoginHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/login-history")
@RequiredArgsConstructor
public class LoginHistoryController {

    private final LoginHistoryService
            loginHistoryService;

    @GetMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public List<LoginHistoryResponse>
    getLoginHistory() {

        return loginHistoryService
                .getLoginHistory();
    }
}