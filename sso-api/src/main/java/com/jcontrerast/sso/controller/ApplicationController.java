package com.jcontrerast.sso.controller;

import com.jcontrerast.sso.core.ApplicationService;
import com.jcontrerast.sso.core.RoleService;
import com.jcontrerast.sso.model.Application;
import com.jcontrerast.sso.model.Role;
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
@RequestMapping("/api/v1/applications")
public class ApplicationController {
    private final ApplicationService service;
    private final RoleService roleService;

    public ApplicationController(
            ApplicationService service,
            RoleService roleService) {
        this.service = service;
        this.roleService = roleService;
    }

    @GetMapping
    public Page<Application> getAll(@Validated PageFilterDTO filter) {
        return service.getAll(filter);
    }

    @GetMapping("/{id}")
    public Application getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @GetMapping("/{id}/roles")
    public Page<Role> getRolesById(@PathVariable UUID id, @Validated PageFilterDTO filter) {
        return roleService.getAllByApplicationId(id, filter);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Application create(@Validated(Create.class) @RequestBody Application application) {
        return service.create(application);
    }

    @PatchMapping
    public Application update(@Validated(Update.class) @RequestBody Application application) {
        return service.update(application);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @PatchMapping("/{id}/clientSecret")
    public void updateClientSecret(@PathVariable UUID id, @RequestParam String clientSecret) {
        service.updateClientSecret(id, clientSecret);
    }

    @PostMapping("/{id}/logo")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadLogo(@PathVariable UUID id, @RequestParam MultipartFile file) {
        service.uploadLogo(id, file);
    }
}
