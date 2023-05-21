package kz.arb.ecoplaning.models;

public class ResponseToken {
    public UserDto userDto;
    public String token;

    public ResponseToken(UserDto userDto, String token) {
        this.userDto = userDto;
        this.token = token;
    }

    public ResponseToken() {
    }
}
