package com.conway.backend.security.userdetails;

import com.conway.backend.admin.entity.Admin;
import com.conway.backend.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService
        implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Admin admin =
                adminRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new UsernameNotFoundException(
                                        "Admin not found"
                                ));

        return new CustomUserDetails(admin);

    }

}