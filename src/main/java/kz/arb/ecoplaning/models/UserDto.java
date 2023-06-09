package kz.arb.ecoplaning.models;


import kz.arb.ecoplaning.models.enums.Role;

import java.util.List;
import java.util.Set;

public class UserDto {
    public long id;
    public String email;
    public String fio;
    public String phone;
    public Set<Role> role;
    public Integer rating;
    public City city;
    public String description;
    public List<Long> imgIds;
    public boolean isOrganizerActive;
    public boolean isAdmin;
    public boolean isOrganizer;
}
