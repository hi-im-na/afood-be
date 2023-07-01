package bkdn.afoodbe.service;

import bkdn.afoodbe.dto.CreateStaffDTO;
import bkdn.afoodbe.dto.StaffDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface IAdminService {
    List<StaffDTO> getAllStaff();

    StaffDTO createStaff(CreateStaffDTO dto);

    StaffDTO updateStaff(StaffDTO dto);

    void updateStaffRoleByUsername(String username, String role);

    void updateStaffRoleById(int id, String role);

    void deleteStaff(String username);

    void deleteStaffById(int id);

    StaffDTO updateSalary(int id, BigDecimal salary);

    BigDecimal totalSalaryByStaffIdAndMonthWorked(Integer id, Integer month);

    BigDecimal totalHoursWorkedInMonth(Integer id, Integer month);

    BigDecimal totalHoursWorkedInYear(Integer id, Integer year);

    BigDecimal totalHoursWorkedInMonthAndYear(Integer id, Integer month, Integer year);
    BigDecimal totalHoursWorkedAllTimeByStaffId(Integer id);

    Map<Integer, BigDecimal> totalHoursWorkedAllTimeOfAllStaff();

    BigDecimal totalMoneyMadeForRestaurantAllTime(Integer id);

}
