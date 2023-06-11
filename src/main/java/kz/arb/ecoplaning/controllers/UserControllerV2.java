package kz.arb.ecoplaning.controllers;

import kz.arb.ecoplaning.models.*;
import kz.arb.ecoplaning.models.enums.Role;
import kz.arb.ecoplaning.services.AuthenticationService;
import kz.arb.ecoplaning.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserControllerV2 {
    private AuthenticationService authenticationService;
    private UserService userService;
    @Autowired
    public UserControllerV2(AuthenticationService authenticationService,
                            UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("/getAllUsers")
    private ResponseEntity<ReturnFilter> getAllUsers(@RequestBody EventFilterPage filterPage) {
        ReturnFilter re = userService.getUserDtoByTokenAndRole(filterPage, Role.ROLE_USER.name());
        return new ResponseEntity<>(re, HttpStatus.OK);
    }

    @PostMapping("/getAllOrganizer")
    private ResponseEntity<ReturnFilter> getAllOrganizer(@RequestBody EventFilterPage filterPage) {
        ReturnFilter re = userService.getUserDtoByTokenAndRole(filterPage, Role.ROLE_ORGANIZER.name());
        return new ResponseEntity<>(re, HttpStatus.OK);
    }

    @PostMapping("/deleteUser")
    private ResponseEntity<Void> deleteUser(@RequestHeader("Authorization") String token,
                                            @RequestParam Long userId) {

        userService.deleteUser(token, userId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/getUserById")
    private ResponseEntity<UserDto> getUserById(@RequestParam Long userId) {

        UserDto userDto = userService.findUserById(userId).getDto();
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }


}
