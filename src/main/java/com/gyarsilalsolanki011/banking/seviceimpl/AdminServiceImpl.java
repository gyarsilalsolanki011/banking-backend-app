package com.gyarsilalsolanki011.banking.seviceimpl;

import com.gyarsilalsolanki011.banking.dto.AdminDto;
import com.gyarsilalsolanki011.banking.entity.Admin;
import com.gyarsilalsolanki011.banking.mapper.AdminMapper;
import com.gyarsilalsolanki011.banking.repository.AdminRepository;
import com.gyarsilalsolanki011.banking.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;

public class AdminServiceImpl implements AdminService {
    /*@Autowired
    private AdminRepository adminRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public AdminDto createAdmin(AdminDto adminDto, String password) {
        Admin admin = new Admin(
                adminDto.getUsername(),
                adminDto.getEmail(),
                passwordEncoder.encode(password),
                adminDto.getRole()
        );
        adminRepository.save(admin);
        return AdminMapper.mapToAdminDto(admin);
    }

    @Override
    public AdminDto getAdminById(Long adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        return AdminMapper.mapToAdminDto(admin);
    }

    @Override
    public List<AdminDto> getAllAdmins() {
        return adminRepository.findAll()
                .stream()
                .map(AdminMapper::mapToAdminDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAdmin(Long adminId) {
        if (!adminRepository.existsById(adminId)) {
            throw new RuntimeException("Admin not found");
        }
        adminRepository.deleteById(adminId);
    }*/
}
