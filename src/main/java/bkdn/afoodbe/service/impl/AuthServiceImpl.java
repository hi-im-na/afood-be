package bkdn.afoodbe.service.impl;

import bkdn.afoodbe.dto.CreateStaffDTO;
import bkdn.afoodbe.entity.Staff;
import bkdn.afoodbe.exception.HttpError;
import bkdn.afoodbe.exception.UnauthorizedError;
import bkdn.afoodbe.jwt.JwtTokenProvider;
import bkdn.afoodbe.repository.StaffRepository;
import bkdn.afoodbe.service.IAuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AuthServiceImpl implements IAuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final StaffRepository staffRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String login(String username, String password, HttpServletResponse response) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.generateToken(username);
        } catch (AuthenticationException e) {
            throw new UnauthorizedError("Invalid username or password");
        }
    }
}
