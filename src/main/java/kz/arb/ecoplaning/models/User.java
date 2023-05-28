package kz.arb.ecoplaning.models;

import kz.arb.ecoplaning.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "active")
    private boolean active;
    @Column(name = "city")
    private String city;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Image> images = new ArrayList<>();
    @Column(name = "password")
    private String password;
    @Column(name = "Description")
    private String description;
    @Column(name = "rating")
    private Integer rating;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name="user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Event> events = new ArrayList<>();
//    @ManyToMany(cascade = CascadeType.ALL)
//    private List<Event> signed;
    public boolean isAdmin(){
        return roles.contains(Role.ROLE_ADMIN);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public String getFio() {
        return firstName + " " + lastName;
    }

    public UserDto getDto() {
        UserDto userDto = new UserDto();
        userDto.id = this.getId();
        userDto.fio = this.getFio();
        userDto.email = this.getEmail();
        userDto.role = this.getRoles();
        userDto.rating = this.getRating();
        userDto.phone = this.getPhoneNumber();
        userDto.imgIds = new ArrayList<>();
        userDto.description = this.getDescription();
        for (Image image: this.images){
            userDto.imgIds.add(image.getId());
        }
        return userDto;
    }



}
