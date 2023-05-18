package bkdn.afoodbe.service;

import bkdn.afoodbe.dto.CreateStaffDTO;
import jakarta.servlet.http.HttpServletResponse;

public interface IAuthService {
    String login(String username, String password, HttpServletResponse response);
}
