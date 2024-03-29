package bkdn.afoodbe.service;

import bkdn.afoodbe.dto.StaffDTO;

public interface IStaffService {
    StaffDTO getStaffInfo(String username);
    void updateStaffInfo(String username, String password, String full_name, String phone_number, String citizen_id);
}
