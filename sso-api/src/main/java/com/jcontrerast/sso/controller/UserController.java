package com.jcontrerast.sso.controller;

import com.jcontrerast.sso.core.AuthorizationService;
import com.jcontrerast.sso.core.UserService;
import com.jcontrerast.sso.model.Role;
import com.jcontrerast.sso.model.User;
import com.jcontrerast.utils.dto.PageFilterDTO;
import com.jcontrerast.utils.validation.Create;
import com.jcontrerast.utils.validation.Update;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService service;
    private final AuthorizationService authorizationService;

    public UserController(
            UserService service,
            AuthorizationService authorizationService) {
        this.service = service;
        this.authorizationService = authorizationService;
    }

    @GetMapping
    public Page<User> getAll(@Validated PageFilterDTO filter) {
        return service.getAll(filter);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @GetMapping("/{id}/roles")
    public Page<Role> getRolesById(@PathVariable UUID id, @Validated PageFilterDTO filter) {
        return authorizationService.getAllByUserId(id, filter);
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

    @PatchMapping("/{id}/password")
    public void updatePassword(@PathVariable UUID id, @RequestParam String password) {
        service.updatePassword(id, password);
    }

    @PostMapping("/{id}/profilePicture")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadProfilePicture(@PathVariable UUID id, @RequestParam MultipartFile file) {
        service.uploadProfilePicture(id, file);
    }
}
