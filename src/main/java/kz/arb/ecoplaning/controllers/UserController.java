package kz.arb.ecoplaning.controllers;


import kz.arb.ecoplaning.models.User;
import kz.arb.ecoplaning.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("registration")
    public String registration(User user, Model model){
        if(!userService.createUser(user)){
            model.addAttribute("error message", "User with email: "
                    +user.getEmail()+ "already exits");
            return "registration";
        }

        return "redirect: /login";
    }

    @GetMapping("/hello")
    public String securityUrl(){
        return "hello";
    }
}
