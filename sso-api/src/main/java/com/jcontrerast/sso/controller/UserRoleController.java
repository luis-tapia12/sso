package com.jcontrerast.sso.controller;

import com.jcontrerast.sso.core.UserRoleService;
import com.jcontrerast.sso.model.UserRole;
import com.jcontrerast.sso.model.id.UserRoleId;
import com.jcontrerast.utils.dto.PageFilterDTO;
import com.jcontrerast.utils.validation.Create;
import com.jcontrerast.utils.validation.Update;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/authorizations")
public class UserRoleController {
    public final UserRoleService service;

    public UserRoleController(UserRoleService service) {
        this.service = service;
    }

    @GetMapping("/{userId}/roles")
    public Page<UserRole> getAllByUserId(@PathVariable UUID userId, @Validated PageFilterDTO filter) {
        return service.getAllByUserId(userId, filter);
    }

    @GetMapping("/{roleId}/users")
    public Page<UserRole> getAllByRoleId(@PathVariable UUID roleId, @Validated PageFilterDTO filter) {
        return service.getAllByRoleId(roleId, filter);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserRole create(@Validated(Create.class) @RequestBody UserRole userRole) {
        return service.create(userRole);
    }

    @PatchMapping
    public UserRole update(@Validated(Update.class) @RequestBody UserRole userRole) {
        return service.update(userRole);
    }

    @DeleteMapping("/{userId}/{roleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID userId, @PathVariable UUID roleId) {
        UserRoleId id = new UserRoleId(userId, roleId);
        service.delete(id);
    }
}
