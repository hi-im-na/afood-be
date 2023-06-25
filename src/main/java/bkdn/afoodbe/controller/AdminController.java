package bkdn.afoodbe.controller;

import bkdn.afoodbe.dto.CreateStaffDTO;
import bkdn.afoodbe.dto.StaffDTO;
import bkdn.afoodbe.service.IAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final IAdminService adminService;

    @GetMapping("/staffs")
    public ResponseEntity<Object> getAllStaff() {
        List<StaffDTO> staff = adminService.getAllStaff();
        return ResponseEntity.ok(staff);
    }

    @PostMapping("/staffs/create")
    public ResponseEntity<Object> createStaff(CreateStaffDTO createStaffDTO) {
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
}
