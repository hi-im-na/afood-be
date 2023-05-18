package bkdn.afoodbe.service;

import bkdn.afoodbe.dto.CreateStaffDTO;
import bkdn.afoodbe.dto.StaffDTO;

import java.util.List;

public interface IAdminService {
    List<StaffDTO> getAllStaff();
    StaffDTO createStaff(CreateStaffDTO dto);
    void updateStaffRole(String username, String role);
    void deleteStaff(String username);
}
