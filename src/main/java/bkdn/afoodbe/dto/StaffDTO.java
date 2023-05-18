package bkdn.afoodbe.dto;

import bkdn.afoodbe.entity.Staff;
import bkdn.afoodbe.model.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StaffDTO {
    private Integer id;
    private String username;
    private Role role;
    private String fullName;
    private String phoneNumber;
    private String citizenId;

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
