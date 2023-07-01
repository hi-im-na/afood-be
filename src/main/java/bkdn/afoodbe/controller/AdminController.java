package bkdn.afoodbe.controller;

import bkdn.afoodbe.dto.CreateNgayCongDto;
import bkdn.afoodbe.dto.CreateStaffDTO;
import bkdn.afoodbe.dto.NgayCongDto;
import bkdn.afoodbe.dto.StaffDTO;
import bkdn.afoodbe.service.IAdminService;
import bkdn.afoodbe.service.NgayCongService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final IAdminService adminService;
    private final NgayCongService ngayCongService;

    @GetMapping("/staffs")
    public ResponseEntity<Object> getAllStaff() {
        List<StaffDTO> staff = adminService.getAllStaff();
        return ResponseEntity.ok(staff);
    }

    @GetMapping("/staffs/getTotalSalaryByStaffIdAndMonthWorked/{staffId}/{month}")
    public ResponseEntity<Object> getTotalSalaryByStaffIdAndMonthWorked(@PathVariable("staffId") Integer staffId, @PathVariable("month") Integer month) {
        System.out.println(staffId + " " + month);
        BigDecimal totalSalary = adminService.totalSalaryByStaffIdAndMonthWorked(staffId, month);
        System.out.println(totalSalary);
        return ResponseEntity.ok(totalSalary);
    }

    @PostMapping("/staffs/create")
    public ResponseEntity<Object> createStaff(CreateStaffDTO createStaffDTO) {
        System.out.println(createStaffDTO);
        StaffDTO staffDTO = adminService.createStaff(createStaffDTO);
        return ResponseEntity.ok(staffDTO);
    }

    @DeleteMapping("/staffs/deleteByUsername/{username}")
    public ResponseEntity<Object> deleteStaff(@PathVariable("username") String username) {
        adminService.deleteStaff(username);
        return ResponseEntity.ok("Delete success");
    }

    @DeleteMapping("/staffs/deleteById/{id}")
    public ResponseEntity<Object> deleteStaffById(@PathVariable("id") int id) {
        adminService.deleteStaffById(id);
        return ResponseEntity.ok("Delete success");
    }

    @PutMapping("/staffs/update")
    public ResponseEntity<Object> updateStaff(StaffDTO staffDTO) {
        StaffDTO staff = adminService.updateStaff(staffDTO);
        return ResponseEntity.ok(staff);
    }

    @PutMapping("/staffs/{username}/roleByUsername")
    public ResponseEntity<Object> updateStaffRole(@PathVariable("username") String username, @RequestParam("role") String role) {
        adminService.updateStaffRoleByUsername(username, role);
        return ResponseEntity.ok("Update success");
    }

    @PutMapping("/staffs/{id}/roleById")
    public ResponseEntity<Object> updateStaffRole(@PathVariable("id") int id, @RequestParam("role") String role) {
        adminService.updateStaffRoleById(id, role);
        return ResponseEntity.ok("Update success");
    }

    @PutMapping("/staffs/{id}/salary")
    public ResponseEntity<Object> updateSalary(@PathVariable("id") int id, @RequestParam("salary") String salary) {
        StaffDTO staffDTO = adminService.updateSalary(id, new BigDecimal(salary));
        return ResponseEntity.ok(staffDTO);
    }

    @GetMapping("/ngaycong")
    public ResponseEntity<Object> getAllNgayCong() {
        List<NgayCongDto> ngayCongDtoList = ngayCongService.findAllNgayCong();
        return ResponseEntity.ok(ngayCongDtoList);
    }

    @GetMapping("/ngaycong/{staffId}")
    public ResponseEntity<Object> getNgayCong(@PathVariable("staffId") int id) {
        List<NgayCongDto> ngayCongDtoList = ngayCongService.findByStaffId(id);
        return ResponseEntity.ok(ngayCongDtoList);
    }

    @GetMapping("/ngaycong/{staffId}/sumTimeWorked")
    public ResponseEntity<Object> sumTimeWorked(@PathVariable("staffId") int id) {
        BigDecimal sum = ngayCongService.sumTimeWorkedByStaffId(id);
        return ResponseEntity.ok(sum);
    }

    @GetMapping("/ngaycong/{staffId}/sum/{month}")
    public ResponseEntity<Object> sumTimeWorkedByStaffIdAndMonth(@PathVariable("staffId") int id, @PathVariable("month") int month) {
        BigDecimal sum = ngayCongService.sumTimeWorkedByStaffIdAndMonth(id, month);
        return ResponseEntity.ok(sum);
    }

    @PostMapping("/ngaycong/create")
    public ResponseEntity<Object> createNgayCong(CreateNgayCongDto createNgayCongDto) {
        NgayCongDto ngayCongDto = ngayCongService.createNgayCong(createNgayCongDto);
        return ResponseEntity.ok(ngayCongDto);
    }

    @DeleteMapping("/ngaycong/delete/{id}")
    public ResponseEntity<Object> deleteNgayCong(@PathVariable("id") int id) {
        ngayCongService.deleteNgayCong(id);
        return ResponseEntity.ok("Delete success");
    }

    @GetMapping("/ngaycong/totalHoursWorkedInMonth/{staffId}/{month}")
    public ResponseEntity<Object> totalHoursWorkedInMonth(@PathVariable Integer month, @PathVariable Integer staffId) {
        BigDecimal totalHoursWorkedInMonth = adminService.totalHoursWorkedInMonth(staffId, month);
        return ResponseEntity.ok(totalHoursWorkedInMonth);
    }

    @GetMapping("/ngaycong/totalHoursWorkedInYear/{staffId}/{year}")
    public ResponseEntity<Object> totalHoursWorkedInYear(@PathVariable Integer year, @PathVariable Integer staffId) {
        BigDecimal totalHoursWorkedInYear = adminService.totalHoursWorkedInYear(staffId, year);
        return ResponseEntity.ok(totalHoursWorkedInYear);
    }

    @GetMapping("/ngaycong/totalHoursWorkedInMonthAndYear/{staffId}/{month}/{year}")
    public ResponseEntity<Object> totalHoursWorkedInMonthAndYear(@PathVariable Integer month, @PathVariable Integer year, @PathVariable Integer staffId) {
        BigDecimal totalHoursWorkedInMonthAndYear = adminService.totalHoursWorkedInMonthAndYear(staffId, month, year);
        return ResponseEntity.ok(totalHoursWorkedInMonthAndYear);
    }

    @GetMapping("/ngaycong/totalHoursWorkedAllTimeByStaffId/{staffId}")
    public ResponseEntity<Object> totalHoursWorkedAllTime(@PathVariable Integer staffId) {
        BigDecimal totalHoursWorkedAllTime = adminService.totalHoursWorkedAllTimeByStaffId(staffId);
        return ResponseEntity.ok(totalHoursWorkedAllTime);
    }

    /**
     * @return Map<StaffDTO, BigDecimal> key: StaffDTO, value: totalHoursWorkedAllTime
     */
    @GetMapping("/ngaycong/totalHoursWorkedAllTimeOfAllStaff")
    public ResponseEntity<Object> totalHoursWorkedAllTimeOfAllStaff() {
        Map<Integer, BigDecimal> mapTotalHoursWorkedAllTime = adminService.totalHoursWorkedAllTimeOfAllStaff();
        return ResponseEntity.ok(mapTotalHoursWorkedAllTime);
    }

    @GetMapping("/ngaycong/totalMoneyMadeForRestaurantAllTime/{staffId}")
    public ResponseEntity<Object> totalMoneyMadeForRestaurantAllTime(@PathVariable Integer staffId) {
        BigDecimal totalMoneyMadeForRestaurantAllTime = adminService.totalMoneyMadeForRestaurantAllTime(staffId);
        return ResponseEntity.ok(totalMoneyMadeForRestaurantAllTime);
    }


}
