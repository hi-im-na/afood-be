package bkdn.afoodbe.controller;

import bkdn.afoodbe.dto.StaffDTO;
import bkdn.afoodbe.service.IStaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@PreAuthorize("isAuthenticated()")
public class AccountController {
    private final IStaffService staffService;

    @GetMapping("/whoiam")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> getCurrentUserInfo(
            Authentication authentication) {

        String username = authentication.getName();

        StaffDTO staffDTO = staffService.getStaffInfo(username);

        return ResponseEntity.ok(staffDTO);
    }
}
