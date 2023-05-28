package kz.arb.ecoplaning.controllers;

import kz.arb.ecoplaning.models.AuthUserDto;
import kz.arb.ecoplaning.models.ResponseToken;
import kz.arb.ecoplaning.models.User;
import kz.arb.ecoplaning.models.UserDto;
import kz.arb.ecoplaning.services.AuthenticationService;
import kz.arb.ecoplaning.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.PermitAll;
import java.util.List;


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
        UserDto userDto = userService.getUserDto(token);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }


}
