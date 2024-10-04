package com.jcontrerast.sso.service;

import com.jcontrerast.sso.core.AuthorizationService;
import com.jcontrerast.sso.model.Authorization;
import com.jcontrerast.sso.repository.AuthorizationRepository;
import com.jcontrerast.utils.Assertions;
import com.jcontrerast.utils.Utils;
import com.jcontrerast.utils.dto.PageFilterDTO;
import com.jcontrerast.utils.exception.ResourceAlreadyExistsException;
import com.jcontrerast.utils.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    public final AuthorizationRepository repository;

    public AuthorizationServiceImpl(AuthorizationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Authorization> getAllByUserId(UUID userId, PageFilterDTO filter) {
        Pageable pageable = Utils.getPageable(filter);

        return repository.findByUserId(userId, pageable);
    }

    @Override
    public Page<Authorization> getAllByRoleId(UUID roleId, PageFilterDTO filter) {
        Pageable pageable = Utils.getPageable(filter);

        return repository.findByRoleId(roleId, pageable);
    }

    @Override
    public Authorization create(Authorization authorization) {
        authorization.setId(UUID.randomUUID());
        if (!repository.existsById(authorization.getId())) {
            return repository.save(authorization);
        } else {
            throw new ResourceAlreadyExistsException("The user role with id " + authorization.getId() + " already exists");
        }
    }

    @Override
    public Authorization update(Authorization authorization) {
        Assertions.isNotNull(authorization.getId());
        Optional<Authorization> source = repository.findById(authorization.getId());

        if (source.isPresent()) {
            Utils.copyNotNull(authorization, source.get());
            return repository.save(source.get());
        } else {
            throw new ResourceNotFoundException("The user role with id " + authorization.getId() + " does not exist");
        }
    }

    @Override
    public void delete(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("The user role with id " + id + " does not exist");
        }
    }
}
