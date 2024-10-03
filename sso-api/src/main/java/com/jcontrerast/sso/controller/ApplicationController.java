package com.jcontrerast.sso.controller;

import com.jcontrerast.sso.core.ApplicationService;
import com.jcontrerast.sso.model.Application;
import com.jcontrerast.utils.dto.PageFilterDTO;
import com.jcontrerast.utils.validation.Create;
import com.jcontrerast.utils.validation.Update;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/applications")
public class ApplicationController {
    private final ApplicationService service;

    public ApplicationController(ApplicationService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Application> getAll(@Validated PageFilterDTO filter) {
        return service.getAll(filter);
    }

    @GetMapping("/{id}")
    public Application getById(@PathVariable UUID id) {
        return service.getById(id);
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
}
