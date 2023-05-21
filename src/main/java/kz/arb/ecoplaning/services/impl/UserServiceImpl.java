package kz.arb.ecoplaning.services.impl;


import kz.arb.ecoplaning.models.User;
import kz.arb.ecoplaning.models.enums.Role;
import kz.arb.ecoplaning.repositories.UserRepository;
import kz.arb.ecoplaning.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public boolean createUser(User user){
        if(userRepository.findUserByEmail(user.getEmail()).isEmpty()){
            user.setActive(true);
            Optional.ofNullable(user.getPassword()).filter(StringUtils::hasText)
                    .map(passwordEncoder::encode)
                    .ifPresent(user::setPassword);
            if (user.getRoles().contains(Role.ROLE_ADMIN)){
                throw new RuntimeException("вы не можете быть Админом");
            }
            user.setActive(true);
            userRepository.save(user);
            log.info("Saving new User with email{}:", user.getEmail());
            return true;
        }
        return false;
    }

    public List<User> list(){
        return userRepository.findAll();
    }

    public void banUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user != null) {
            if(user.isActive()){
                user.setActive(false);
                log.info("Ban user with id={}; email: {}", user.getId(), user.getEmail());
            }else user.setActive(true);
            log.info("Reban user with id={}; email: {}", user.getId(), user.getEmail());
        }
        userRepository.save(user);

    }

    public void changeUserRoles(User user, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for(String key: form.keySet()){
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Faild to retrive email : " + email));
    }
}

