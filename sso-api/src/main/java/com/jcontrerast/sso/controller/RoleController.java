package com.jcontrerast.sso.controller;

import com.jcontrerast.sso.core.RoleService;
import com.jcontrerast.sso.model.Role;
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
    public final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Role> getAll(@Validated PageFilterDTO filter) {
        return service.getAll(filter);
    }

    @GetMapping("/{applicationId}")
    public Page<Role> getAllByApplicationId(@PathVariable UUID applicationId, @Validated PageFilterDTO filter) {
        return service.getAllByApplicationId(applicationId, filter);
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
