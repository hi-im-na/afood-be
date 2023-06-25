package bkdn.afoodbe.service;

import bkdn.afoodbe.dto.CreateStaffDTO;
import bkdn.afoodbe.dto.StaffDTO;

import java.math.BigDecimal;
import java.util.List;

public interface IAdminService {
    List<StaffDTO> getAllStaff();

    StaffDTO createStaff(CreateStaffDTO dto);

    void updateStaffRoleByUsername(String username, String role);

    void updateStaffRoleById(int id, String role);

    void deleteStaff(String username);

    void deleteStaffById(int id);
    StaffDTO updateSalary(int id, BigDecimal salary);
}
