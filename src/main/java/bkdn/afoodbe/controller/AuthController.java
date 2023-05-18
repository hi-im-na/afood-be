package bkdn.afoodbe.controller;

import bkdn.afoodbe.dto.JwtTokenDTO;
import bkdn.afoodbe.service.IAuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AuthController {
    private final IAuthService authService;
    private long JWT_EXPIRATION = 86400000L;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestParam("username") String username,
                                        @RequestParam("password") String password,
                                        HttpServletResponse response) {
        System.out.println("username: " + username);
        String accessToken = authService.login(username, password, response);
        return ResponseEntity.ok(new JwtTokenDTO(accessToken, JWT_EXPIRATION));
    }
}
