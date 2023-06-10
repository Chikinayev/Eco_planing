package kz.arb.ecoplaning.controllers;

import kz.arb.ecoplaning.models.*;
import kz.arb.ecoplaning.services.AuthenticationService;
import kz.arb.ecoplaning.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private AuthenticationService authenticationService;
    private UserService userService;
    @Autowired
    public AuthenticationController(AuthenticationService authenticationService,
                                    UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("/login")
    @PermitAll
    private ResponseEntity<ResponseToken> login(@RequestParam String email, @RequestParam String password) {
        ResponseToken token = authenticationService.login(email, password);

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/registration")
    private ResponseEntity<ResponseToken> register(@RequestBody User user) {
        ResponseToken token = authenticationService.register(user);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/userDto")
    private ResponseEntity<UserDto> getUserDto(@RequestHeader("Authorization") String token ) {
        UserDto userDto = userService.getUserDtoByTokenAndRole(token);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }


}
