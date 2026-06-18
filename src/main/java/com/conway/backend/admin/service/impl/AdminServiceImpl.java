package com.conway.backend.admin.service.impl;

import com.conway.backend.admin.dto.request.CreateAdminRequest;
import com.conway.backend.admin.dto.request.UpdateAdminRequest;
import com.conway.backend.admin.dto.response.AdminResponse;
import com.conway.backend.admin.entity.Admin;
import com.conway.backend.admin.entity.Role;
import com.conway.backend.admin.mapper.AdminMapper;
import com.conway.backend.admin.repository.AdminRepository;
import com.conway.backend.admin.repository.RoleRepository;
import com.conway.backend.admin.service.AdminService;
import com.conway.backend.audit.service.AuditLogService;
import com.conway.backend.exception.BadRequestException;
import com.conway.backend.exception.ResourceNotFoundException;
import com.conway.backend.security.userdetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl
        implements AdminService {

    private final AdminRepository adminRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuditLogService auditLogService;

    @Transactional
    @Override
    public AdminResponse createAdmin(
            CreateAdminRequest request
    ) {

        if (adminRepository.existsByEmail(
                request.getEmail()
        )) {

            throw new BadRequestException(
                    "Email already exists"
            );
        }

        if (adminRepository.existsByPhoneNumber(
                request.getPhoneNumber()
        )) {

            throw new BadRequestException(
                    "Phone number already exists"
            );
        }

        Role role =
                roleRepository.findByRoleName(
                                request.getRoleName()
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Role not found"
                                )
                        );

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        CustomUserDetails currentUser =
                (CustomUserDetails)
                        authentication.getPrincipal();

        Admin creator =
                currentUser.getAdmin();

        Admin admin = Admin.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .passwordHash(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )
                .role(role)
                .createdBy(creator)
                .isActive(true)
                .build();

        Admin savedAdmin =
                adminRepository.save(admin);

        auditLogService.log(
                creator,
                "ADMIN_CREATED",
                "Admin",
                savedAdmin.getId(),
                "Created admin: " + savedAdmin.getEmail()
        );

        return AdminMapper.toResponse(
                savedAdmin
        );
    }
    @Transactional(readOnly = true)
    @Override
    public List<AdminResponse> getAllAdmins() {
        return adminRepository.findAll()
                .stream()
                .map(AdminMapper::toResponse)
                .toList();
    }
    @Transactional(readOnly = true)
    @Override
    public AdminResponse getAdminById(Long id) {

        Admin admin =
                adminRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Admin not found"
                                )
                        );

        return AdminMapper.toResponse(admin);

    }
    @Override
    @Transactional
    public void toggleAdminStatus(Long id) {

        Admin admin =
                adminRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Admin not found"
                                )
                        );

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        CustomUserDetails currentUser =
                (CustomUserDetails)
                        authentication.getPrincipal();

        Admin actor =
                currentUser.getAdmin();

        if (
                actor.getId().equals(id)
        ) {

            throw new BadRequestException(
                    "You cannot deactivate your own account"
            );
        }

        if (
                Boolean.TRUE.equals(
                        admin.getIsActive()
                )
                        &&
                        "ROLE_SUPER_ADMIN".equals(
                                admin.getRole()
                                        .getRoleName()
                        )
        ) {

            long activeSuperAdmins =
                    adminRepository
                            .countByRoleRoleNameAndIsActive(
                                    "ROLE_SUPER_ADMIN",
                                    true
                            );

            if (activeSuperAdmins <= 1) {

                throw new BadRequestException(
                        "Cannot deactivate the last active super admin"
                );
            }
        }

        boolean newStatus =
                !Boolean.TRUE.equals(
                        admin.getIsActive()
                );

        admin.setIsActive(newStatus);

        admin.setStatusChangedAt(
                LocalDateTime.now()
        );

        Admin savedAdmin =
                adminRepository.save(admin);

        auditLogService.log(
                actor,
                newStatus
                        ? "ADMIN_ACTIVATED"
                        : "ADMIN_DEACTIVATED",
                "ADMIN",
                savedAdmin.getId(),
                "Admin status changed to "
                        + (newStatus ? "ACTIVE" : "INACTIVE")
        );

    }

    @Transactional
    @Override
    public AdminResponse updateAdmin(
            Long id,
            UpdateAdminRequest request
    ) {

        Admin admin =
                adminRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Admin not found"
                                )
                        );

        if (
                adminRepository.existsByPhoneNumberAndIdNot(
                        request.getPhoneNumber(),
                        id
                )
        ) {
            throw new IllegalArgumentException(
                    "Phone number already exists"
            );
        }

        Role role =
                roleRepository.findByRoleName(
                                request.getRoleName()
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Role not found"
                                )
                        );

        admin.setFullName(
                request.getFullName()
        );

        admin.setPhoneNumber(
                request.getPhoneNumber()
        );

        admin.setRole(role);

        Admin savedAdmin =
                adminRepository.save(admin);

        auditLogService.log(
                savedAdmin,
                "ADMIN_UPDATED",
                "ADMIN",
                savedAdmin.getId(),
                "Admin updated: "
                        + savedAdmin.getEmail()
        );

        return AdminMapper.toResponse(
                savedAdmin
        );
    }
}