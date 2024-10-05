package com.jcontrerast.sso.service;

import com.jcontrerast.sso.core.PermissionService;
import com.jcontrerast.sso.model.Permission;
import com.jcontrerast.sso.repository.PermissionRepository;
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
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository repository;

    public PermissionServiceImpl(PermissionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Permission> getAll(PageFilterDTO filter) {
        Pageable pageable = Utils.getPageable(filter);

        return repository.findAll(pageable);
    }

    @Override
    public Page<Permission> getAllByRoleId(UUID roleId, PageFilterDTO filter) {
        Pageable pageable = Utils.getPageable(filter);

        return repository.findByRoleId(roleId, pageable);
    }

    @Override
    public Permission create(Permission permission) {
        permission.setId(UUID.randomUUID());
        if (!repository.existsById(permission.getId())) {
            return repository.save(permission);
        } else {
            throw new ResourceAlreadyExistsException("The permission with id " + permission.getId() + " already exists");
        }
    }

    @Override
    public Permission update(Permission permission) {
        Assertions.isNotNull(permission.getId());
        Optional<Permission> source = repository.findById(permission.getId());

        if (source.isPresent()) {
            Utils.copyNotNull(permission, source.get());
            return repository.save(source.get());
        } else {
            throw new ResourceNotFoundException("The permission with id " + permission.getId() + " does not exist");
        }
    }

    @Override
    public void delete(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("The permission with id " + id + " does not exist");
        }
    }
}
