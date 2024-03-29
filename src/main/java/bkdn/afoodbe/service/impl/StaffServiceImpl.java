package bkdn.afoodbe.service.impl;

import bkdn.afoodbe.dto.StaffDTO;
import bkdn.afoodbe.entity.Staff;
import bkdn.afoodbe.repository.StaffRepository;
import bkdn.afoodbe.service.IStaffService;
import bkdn.afoodbe.service.NgayCongService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class StaffServiceImpl implements IStaffService {

    private final StaffRepository staffRepository;
    private final PasswordEncoder passwordEncoder;
    private final NgayCongService ngayCongService;

    @Override
    public StaffDTO getStaffInfo(String username) {
        Staff staff = staffRepository.findByUsername(username);
        if (staff == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff" + username + "not found");
        }
        return StaffDTO.toStaffDTO(staff);
    }

    @Override
    public void updateStaffInfo(String username, String password, String full_name, String phone_number, String citizen_id) {
        Staff staff = staffRepository.findByUsername(username);
        if (staff == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff" + username + "not found");
        }
        if (!staff.getPassword().equals(password)) {
            staff.setPassword(passwordEncoder.encode(password));
        }
        if (!staff.getFullName().equals(full_name)) {
            staff.setFullName(full_name);
        }
        if (!staff.getPhoneNumber().equals(phone_number)) {
            staff.setPhoneNumber(phone_number);
        }
        if (!staff.getCitizenId().equals(citizen_id)) {
            staff.setCitizenId(citizen_id);
        }
    }

}
