package com.conway.backend.admin.service;

import com.conway.backend.admin.dto.request.CreateAdminRequest;
import com.conway.backend.admin.dto.request.UpdateAdminRequest;
import com.conway.backend.admin.dto.response.AdminResponse;

import java.util.List;

public interface AdminService {

    AdminResponse createAdmin(CreateAdminRequest request);

    List<AdminResponse> getAllAdmins();

    AdminResponse getAdminById(Long id);

    void toggleAdminStatus(Long id);

    AdminResponse updateAdmin(Long id, UpdateAdminRequest request);
}