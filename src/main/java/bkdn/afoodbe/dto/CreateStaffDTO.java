package bkdn.afoodbe.dto;

import java.io.Serializable;

public record CreateStaffDTO(String username, String password, String role, String fullName, String phoneNumber,
                             String citizenId) implements Serializable {
}
