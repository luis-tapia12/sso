package com.jcontrerast.sso.controller;

import com.jcontrerast.sso.core.PermissionService;
import com.jcontrerast.sso.model.Permission;
import com.jcontrerast.utils.dto.PageFilterDTO;
import com.jcontrerast.utils.validation.Create;
import com.jcontrerast.utils.validation.Update;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/permissions")
public class PermissionController {
    public final PermissionService service;

    public PermissionController(PermissionService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Permission> getAll(@Validated PageFilterDTO filter) {
        return service.getAll(filter);
    }

    @GetMapping("/{roleId}")
    public Page<Permission> getAllByRoleId(@PathVariable UUID roleId, @Validated PageFilterDTO filter) {
        return service.getAllByRoleId(roleId, filter);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Permission create(@Validated(Create.class) @RequestBody Permission permission) {
        return service.create(permission);
    }

    @PatchMapping
    public Permission update(@Validated(Update.class) @RequestBody Permission permission) {
        return service.update(permission);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
