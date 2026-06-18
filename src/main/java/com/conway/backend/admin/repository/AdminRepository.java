package com.conway.backend.admin.repository;

import com.conway.backend.admin.entity.Admin;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    @EntityGraph(attributePaths = {"role"})
    Optional<Admin> findByEmail(String email);

    @EntityGraph(
            attributePaths = {
                    "role",
                    "createdBy",
                    "updatedBy"
            }
    )
    Optional<Admin> findById(Long id);

    @EntityGraph(
            attributePaths = {
                    "role",
                    "createdBy",
                    "updatedBy"
            }
    )
    List<Admin> findAll();

    @EntityGraph(attributePaths = {"role"})
    List<Admin> findByIsActiveTrue();

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(
            String phoneNumber,
            Long id
    );

    long countByIsActive(Boolean isActive);

    long countByRoleRoleNameAndIsActive(
            String roleName,
            Boolean isActive
    );
}