package kz.arb.ecoplaning.models;

import kz.arb.ecoplaning.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserDto {
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private List<MultipartFile> images;
    private Integer rating;
    private String password;
    private Role role;
    private String city;

}
