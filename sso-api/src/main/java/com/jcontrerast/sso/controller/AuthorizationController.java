package com.jcontrerast.sso.controller;

import com.jcontrerast.sso.core.AuthorizationService;
import com.jcontrerast.sso.model.Authorization;
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
public class AuthorizationController {
    public final AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/{userId}/roles")
    public Page<Authorization> getAllByUserId(@PathVariable UUID userId, @Validated PageFilterDTO filter) {
        return service.getAllByUserId(userId, filter);
    }

    @GetMapping("/{roleId}/users")
    public Page<Authorization> getAllByRoleId(@PathVariable UUID roleId, @Validated PageFilterDTO filter) {
        return service.getAllByRoleId(roleId, filter);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Authorization create(@Validated(Create.class) @RequestBody Authorization authorization) {
        return service.create(authorization);
    }

    @PatchMapping
    public Authorization update(@Validated(Update.class) @RequestBody Authorization authorization) {
        return service.update(authorization);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
