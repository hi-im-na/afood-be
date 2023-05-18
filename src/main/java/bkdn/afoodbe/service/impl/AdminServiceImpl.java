package bkdn.afoodbe.service.impl;

import bkdn.afoodbe.dto.CreateStaffDTO;
import bkdn.afoodbe.dto.StaffDTO;
import bkdn.afoodbe.entity.Staff;
import bkdn.afoodbe.exception.HttpError;
import bkdn.afoodbe.model.Role;
import bkdn.afoodbe.repository.StaffRepository;
import bkdn.afoodbe.service.IAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AdminServiceImpl implements IAdminService {

    private final StaffRepository staffRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<StaffDTO> getAllStaff() {
        List<Staff> staffList = staffRepository.findAll();
        if (staffList.isEmpty()) {
            throw new HttpError("Staff list is empty", HttpStatus.NOT_FOUND);
        }
        return staffList.stream().map(StaffDTO::toStaffDTO).collect(Collectors.toList());
    }

    @Override
    public StaffDTO createStaff(CreateStaffDTO dto) {
        String username = dto.getUsername();
        Staff existStaff = staffRepository.findByUsername(username);
        if (existStaff != null) throw new HttpError("Username" + username + " is already taken", HttpStatus.CONFLICT);

        Staff newStaff = Staff.builder()
                .username(username)
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.ROLE_STAFF)
                .fullName(dto.getFullName())
                .phoneNumber(dto.getPhoneNumber())
                .build();
        staffRepository.saveAndFlush(newStaff);
        return StaffDTO.toStaffDTO(newStaff);
    }


    @Override
    public void updateStaffRole(String username, String role) {
        Staff staff = staffRepository.findByUsername(username);
        if (staff == null) {
            throw new HttpError("Staff" + username + "not found", HttpStatus.NOT_FOUND);
        }
        staff.setRole(Role.valueOf(role));
        staffRepository.saveAndFlush(staff);
    }

    @Override
    public void deleteStaff(String username) {
        Staff staff = staffRepository.findByUsername(username);
        if (staff == null) {
            throw new HttpError("Staff" + username + "not found", HttpStatus.NOT_FOUND);
        }
        staffRepository.delete(staff);
    }
}
