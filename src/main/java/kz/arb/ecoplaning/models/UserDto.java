package kz.arb.ecoplaning.models;


import kz.arb.ecoplaning.models.enums.Role;

import java.util.Set;

public class UserDto {
    public String email;
    public String fio;
    public String phone;
    public Set<Role> role;
    public Integer rating;
}
