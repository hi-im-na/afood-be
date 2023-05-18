package bkdn.afoodbe.dto;

import bkdn.afoodbe.model.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateStaffDTO {
    private String username;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String citizenId;
}
