package bkdn.afoodbe.controller;

import bkdn.afoodbe.dto.CreateStaffDTO;
import bkdn.afoodbe.dto.StaffDTO;
import bkdn.afoodbe.service.IAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/users/{username}")
    public ResponseEntity<Object> createStaff(@PathVariable("username") String username,
                                              @RequestParam String password,
                                              @RequestParam String fullName,
                                              @RequestParam String phoneNumber,
                                              @RequestParam String citizenId
    ) {
        CreateStaffDTO createStaffDTO = CreateStaffDTO.builder()
                .username(username)
                .password(password)
                .fullName(fullName)
                .phoneNumber(phoneNumber)
                .citizenId(citizenId)
                .build();
        StaffDTO staffDTO = adminService.createStaff(createStaffDTO);
        return ResponseEntity.ok(staffDTO);
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity<Object> deleteStaff(@PathVariable("username") String username) {
        adminService.deleteStaff(username);
        return ResponseEntity.ok("Delete success");
    }
}
