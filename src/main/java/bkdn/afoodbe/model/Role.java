package bkdn.afoodbe.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_MANAGER,
    ROLE_STAFF;

    @Override
    public String getAuthority() {
        return name();
    }
}
