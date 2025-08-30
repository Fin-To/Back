package FinTo.global.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityTestController {

    @GetMapping("/test/anonymous")
    public String anonymous() {
        return "Hello Anonymous";
    }

    @GetMapping("/test/authenticated")
    public String authenticated() {
        return "Hello Authenticated";
    }
}
