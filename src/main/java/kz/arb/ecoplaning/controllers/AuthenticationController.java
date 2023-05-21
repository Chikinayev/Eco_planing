package kz.arb.ecoplaning.controllers;

import kz.arb.ecoplaning.models.ResponseToken;
import kz.arb.ecoplaning.models.User;
import kz.arb.ecoplaning.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private AuthenticationService authenticationService;
    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
     this.authenticationService = authenticationService;
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


}
