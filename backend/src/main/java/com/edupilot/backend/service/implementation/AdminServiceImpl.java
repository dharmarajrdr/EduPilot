package com.edupilot.backend.service.implementation;

import com.edupilot.backend.model.Admin;
import com.edupilot.backend.repository.AdminRepository;
import com.edupilot.backend.service.interfaces.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    /**
     * Get an admin
     *
     * @return
     */
    @Override
    public Admin getAdmin() {

        return adminRepository.findAll().getFirst();
    }
}
