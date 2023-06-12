package kz.arb.ecoplaning.services;

import kz.arb.ecoplaning.models.EventFilterPage;
import kz.arb.ecoplaning.models.ReturnFilter;
import kz.arb.ecoplaning.models.User;
import kz.arb.ecoplaning.models.UserDto;

import java.util.List;
import java.util.Map;

public interface UserService {

    boolean createUser(User user);
    List<User> list();
    void banUser(Long id);
    void changeUserRoles(User user, Map<String, String> form);

    User findUserByEmail(String email);

    User findUserById(Long id);

    UserDto getUserDtoByTokenAndRole(String token);

    ReturnFilter getUserDtoByTokenAndRole(EventFilterPage filterPage, String role);

    void createAdminUser();

    void deleteUser(String token, Long userId);

    void changeOrganizerActive(String token, Long userId);


}
