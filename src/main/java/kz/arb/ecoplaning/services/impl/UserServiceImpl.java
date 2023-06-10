package kz.arb.ecoplaning.services.impl;


import kz.arb.ecoplaning.models.EventFilterPage;
import kz.arb.ecoplaning.models.ReturnFilter;
import kz.arb.ecoplaning.models.User;
import kz.arb.ecoplaning.models.UserDto;
import kz.arb.ecoplaning.models.enums.Role;
import kz.arb.ecoplaning.repositories.UserRepository;
import kz.arb.ecoplaning.security.jwt.JwtAuthenticationException;
import kz.arb.ecoplaning.security.jwt.JwtTokenProvider;
import kz.arb.ecoplaning.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    private final JwtTokenProvider jwtTokenProvider;

    public boolean createUser(User user){
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());
        if(userByEmail.isEmpty()){
            user.setActive(true);
            Optional.ofNullable(user.getPassword()).filter(StringUtils::hasText)
                    .map(passwordEncoder::encode)
                    .ifPresent(user::setPassword);
            if (user.getRoles().contains(Role.ROLE_ADMIN) && !user.getEmail().equals("root")){
                throw new RuntimeException("вы не можете быть Админом");
            }
//            user.setRoles();
            user.setActive(true);
            user.setRating(100);
            userRepository.save(user);
            log.info("Saving new User with email{}:", user.getEmail());
            return true;
        } else {
            User existingUser = userByEmail.get();
            if (!existingUser.isActive()) {
                throw new JwtAuthenticationException("Вас Удалили Обращайтесь к Администратору ");
            }
        }
        return false;
    }

    @Override
    public void createAdminUser() {
        System.out.println("------------aa-------");
        User root = User.builder()
                .email("root")
                .password("111")
                .firstName("Super")
                .lastName("Admin")
                .roles(Set.of(Role.ROLE_ADMIN))
                .build();
        createUser(root);
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

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Faild to retrive id : " + id));
    }

    @Override
    public UserDto getUserDtoByTokenAndRole(String token) {
        if (token != null && token.startsWith("Bearer_")) {
            token = token.substring(7);

            String email = jwtTokenProvider.getUserEmail(token);
            User user = findUserByEmail(email);
            return user.getDto();
        }

        return null;
    }

    @Override
    public ReturnFilter getUserDtoByTokenAndRole(EventFilterPage filter, String role) {
        var pageable = PageRequest.of(filter.currentPage, filter.pageSize, Sort.by("id"));

        Page<User> allBy = userRepository.findAllBy(role, pageable);
        ReturnFilter returnFilter = new ReturnFilter();
        returnFilter.userDtos =  new ArrayList<>();

        allBy.stream()
//                .filter(user -> !user.getRoles().contains(Role.ROLE_ADMIN))
                .forEach(value -> returnFilter.userDtos.add(value.getDto()));

        returnFilter.filter = new EventFilterPage();
        returnFilter.filter.totalItems = allBy.getTotalElements();
        returnFilter.filter.currentPage = allBy.getNumber();

        return returnFilter;
    }

    @Override
    public void deleteUser(String token, Long userId) {
        UserDto user = getUserDtoByTokenAndRole(token);
        if (user.isAdmin) {
            User delUser = userRepository.getById(userId);
            delUser.setActive(false);
            userRepository.save(delUser);
        }
    }
}

