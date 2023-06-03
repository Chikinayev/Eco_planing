package kz.arb.ecoplaning;

import kz.arb.ecoplaning.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcoPlaningApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;
    public static void main(String[] args) {
        SpringApplication.run(EcoPlaningApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userService.createAdminUser();
    }
}
