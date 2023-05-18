package bkdn.afoodbe.service;

import jakarta.servlet.http.HttpServletResponse;

public interface IAuthService {
    String login(String username, String password, HttpServletResponse response);
}
