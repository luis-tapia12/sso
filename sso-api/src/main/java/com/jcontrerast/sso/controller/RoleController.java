package com.jcontrerast.sso.controller;

import com.jcontrerast.sso.core.AuthorizationService;
import com.jcontrerast.sso.core.PermissionService;
import com.jcontrerast.sso.core.RoleService;
import com.jcontrerast.sso.model.Permission;
import com.jcontrerast.sso.model.Role;
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
@RequestMapping("/api/v1/roles")
public class RoleController {
    private final RoleService service;
    private final PermissionService permissionService;
    private final AuthorizationService authorizationService;

    public RoleController(
            RoleService service,
            PermissionService permissionService,
            AuthorizationService authorizationService) {
        this.service = service;
        this.permissionService = permissionService;
        this.authorizationService = authorizationService;
    }

    @GetMapping
    public Page<Role> getAll(@Validated PageFilterDTO filter) {
        return service.getAll(filter);
    }

    @GetMapping("/{id}/users")
    public Page<User> getUsersById(@PathVariable UUID id, @Validated PageFilterDTO filter) {
        return authorizationService.getAllByRoleId(id, filter);
    }

    @GetMapping("/{id}/permissions")
    public Page<Permission> getPermissionsById(@PathVariable UUID id, @Validated PageFilterDTO filter) {
        return permissionService.getAllByRoleId(id, filter);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Role create(@Validated(Create.class) @RequestBody Role role) {
        return service.create(role);
    }

    @PatchMapping
    public Role update(@Validated(Update.class) @RequestBody Role role) {
        return service.update(role);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
