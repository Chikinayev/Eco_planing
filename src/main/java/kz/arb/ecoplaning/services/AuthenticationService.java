package kz.arb.ecoplaning.services;

import kz.arb.ecoplaning.models.ResponseToken;
import kz.arb.ecoplaning.models.User;
import kz.arb.ecoplaning.models.UserDto;

public interface AuthenticationService {
    ResponseToken login(String email, String password);

    ResponseToken register(User user);
}
