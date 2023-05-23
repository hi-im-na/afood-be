package bkdn.afoodbe.dto;

import bkdn.afoodbe.entity.Staff;
import bkdn.afoodbe.model.Role;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record StaffDTO(
        Integer id,
        String username,
        Role role,
        String fullName,
        String phoneNumber,
        String citizenId) implements Serializable {
    public static StaffDTO toStaffDTO(Staff staff) {
        return StaffDTO.builder()
                .id(staff.getId())
                .username(staff.getUsername())
                .role(staff.getRole())
                .fullName(staff.getFullName())
                .phoneNumber(staff.getPhoneNumber())
                .citizenId(staff.getCitizenId())
                .build();
    }
}
