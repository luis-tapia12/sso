package com.jcontrerast.sso.controller;

import com.jcontrerast.sso.core.UserService;
import com.jcontrerast.sso.model.User;
import com.jcontrerast.utils.dto.PageFilterDTO;
import com.jcontrerast.utils.validation.Create;
import com.jcontrerast.utils.validation.Update;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public Page<User> getAll(@Validated PageFilterDTO filter) {
        return service.getAll(filter);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Validated(Create.class) @RequestBody User user) {
        return service.create(user);
    }

    @PatchMapping
    public User update(@Validated(Update.class) @RequestBody User user) {
        return service.update(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
