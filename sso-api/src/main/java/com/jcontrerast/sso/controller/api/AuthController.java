package com.jcontrerast.sso.controller.api;

import com.jcontrerast.sso.model.User;
import com.jcontrerast.sso.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @GetMapping("/user")
    public User getLoggedUser(@AuthenticationPrincipal UserDetails user) {
        return service.getUser(user.getUsername());
    }
}
