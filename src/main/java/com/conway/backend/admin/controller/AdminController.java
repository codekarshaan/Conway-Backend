package com.conway.backend.admin.controller;

import com.conway.backend.admin.dto.request.CreateAdminRequest;
import com.conway.backend.admin.dto.request.UpdateAdminRequest;
import com.conway.backend.admin.dto.response.AdminResponse;
import com.conway.backend.admin.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

@RestController
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public AdminResponse createAdmin(
            @Valid @RequestBody CreateAdminRequest request
    ) {

        return adminService.createAdmin(request);

    }

    @GetMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public List<AdminResponse> getAllAdmins() {

        return adminService.getAllAdmins();

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public AdminResponse getAdminById(
            @PathVariable Long id
    ) {

        return adminService.getAdminById(id);

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public AdminResponse updateAdmin(
            @PathVariable Long id,
            @Valid @RequestBody UpdateAdminRequest request
    ) {

        return adminService.updateAdmin(
                id,
                request
        );

    }

    @PatchMapping("/{id}/toggle-status")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public void toggleAdminStatus(
            @PathVariable Long id
    ) {

        adminService.toggleAdminStatus(id);

    }


}