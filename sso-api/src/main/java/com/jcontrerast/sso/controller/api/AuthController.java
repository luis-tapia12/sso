package com.jcontrerast.sso.controller.api;

import com.jcontrerast.sso.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @GetMapping("/user")
    public User getLoggedUser(@AuthenticationPrincipal User user) {
        return user;
    }
}
