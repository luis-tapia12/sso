package com.jcontrerast.sso.controller;

import com.jcontrerast.sso.core.AuthorizationService;
import com.jcontrerast.sso.model.Authorization;
import com.jcontrerast.utils.validation.Create;
import com.jcontrerast.utils.validation.Update;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/authorizations")
public class AuthorizationController {
    private final AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
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
