package com.jcontrerast.sso.controller.api;

import com.jcontrerast.sso.dto.PageDTO;
import com.jcontrerast.sso.model.User;
import com.jcontrerast.sso.service.UserService;
import com.jcontrerast.sso.validation.Create;
import com.jcontrerast.sso.validation.Update;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public Page<User> getUsers(PageDTO pageDTO) {
        return service.getUsers(pageDTO);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        return service.getUser(id);
    }

    @PostMapping
    public User saveUser(@Validated(Create.class) @RequestBody User user) {
        return service.saveUser(user);
    }

    @PatchMapping("/{id}")
    public User updateUser(@PathVariable String id, @Validated(Update.class) @RequestBody User user) {
        user.setId(id);
        return service.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        service.deleteUser(id);
    }
}
