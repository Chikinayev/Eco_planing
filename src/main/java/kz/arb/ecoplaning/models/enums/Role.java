package kz.arb.ecoplaning.models.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN, ROLE_ORGANIZER, ROLE_MODERATOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
