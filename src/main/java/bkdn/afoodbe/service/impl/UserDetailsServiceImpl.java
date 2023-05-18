package bkdn.afoodbe.service.impl;

import bkdn.afoodbe.entity.Staff;
import bkdn.afoodbe.model.UserDetailsImpl;
import bkdn.afoodbe.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserDetailsServiceImpl implements UserDetailsService {

    private final StaffRepository staffRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Staff staff = staffRepository.findByUsername(username);
        if (staff == null) throw new UsernameNotFoundException("User" + username + " not found");
        return new UserDetailsImpl(staff);
    }
}
