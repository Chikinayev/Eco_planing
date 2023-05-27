package kz.arb.ecoplaning.models.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER("Пользователь"),
    ROLE_ADMIN("Администратор"),
    ROLE_ORGANIZER("Организатор"),
    ROLE_MODERATOR("Модератор");

    String value;

    Role(String value) {
        this.value = value;
    }
    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
