package kz.arb.ecoplaning.services;


import kz.arb.ecoplaning.models.User;
import kz.arb.ecoplaning.models.enums.Role;
import kz.arb.ecoplaning.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user){
        if(userRepository.findUserByEmail(user.getEmail())==null){
            user.setActive(true);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.getRoles().add(Role.ROLE_USER);
            userRepository.save(user);
            log.info("Saving new User with email{}:", user.getEmail());
            return true;
        }
        return false;

    }
}
