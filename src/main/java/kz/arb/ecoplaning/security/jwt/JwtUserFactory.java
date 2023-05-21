package kz.arb.ecoplaning.security.jwt;

import kz.arb.ecoplaning.models.User;
import kz.arb.ecoplaning.models.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }
    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getFio(),
                user.isActive(),
                user.getPassword(),
                user.isEnabled(),
                mapToGrantedAuthority(new ArrayList<>(user.getRoles())));
    }
    private static List<GrantedAuthority> mapToGrantedAuthority(List<Role> userRoles) {
        return userRoles.stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
    }


}
