package kz.arb.ecoplaning.services.impl;

import kz.arb.ecoplaning.models.ResponseToken;
import kz.arb.ecoplaning.models.User;
import kz.arb.ecoplaning.security.jwt.JwtTokenProvider;
import kz.arb.ecoplaning.services.AuthenticationService;
import kz.arb.ecoplaning.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationServiceImpl implements AuthenticationService {
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;
    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @Override
    public ResponseToken login(String email, String password) throws AuthenticationException{
        try {
            System.out.println("ooooooooooo");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            User user = userService.findUserByEmail(email);
            if (user == null) {
                throw new UsernameNotFoundException("Email whit email : " + email + " not found");
            }
            System.out.println("qqqqqqqqqqqqqqqqq");
            String token = jwtTokenProvider.createToken(email, user.getRoles());

            return new ResponseToken(user.getDto(), token);
        }catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email or password", e);
        }
    }

    @Override
    public ResponseToken register(User user) {
        if(!userService.createUser(user)){
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }
        String token = jwtTokenProvider.createToken(user.getEmail(), user.getRoles());
        return new ResponseToken(user.getDto(), token);
    }
}
