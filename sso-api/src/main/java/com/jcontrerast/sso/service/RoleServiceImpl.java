package com.jcontrerast.sso.service;

import com.jcontrerast.sso.core.RoleService;
import com.jcontrerast.sso.model.Role;
import com.jcontrerast.sso.repository.RoleRepository;
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
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Role> getAll(PageFilterDTO filter) {
        Pageable pageable = Utils.getPageable(filter);

        return repository.findAll(pageable);
    }

    @Override
    public Page<Role> getAllByApplicationId(UUID applicationId, PageFilterDTO filter) {
        Pageable pageable = Utils.getPageable(filter);

        return repository.findByApplicationId(applicationId, pageable);
    }

    @Override
    public Role create(Role role) {
        role.setId(UUID.randomUUID());
        if (!repository.existsById(role.getId())) {
            return repository.save(role);
        } else {
            throw new ResourceAlreadyExistsException("The role with id " + role.getId() + " already exists");
        }
    }

    @Override
    public Role update(Role role) {
        Assertions.isNotNull(role.getId());
        Optional<Role> source = repository.findById(role.getId());

        if (source.isPresent()) {
            Utils.copyNotNull(role, source.get());
            return repository.save(source.get());
        } else {
            throw new ResourceNotFoundException("The role with id " + role.getId() + " does not exist");
        }
    }

    @Override
    public void delete(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("The role with id " + id + " does not exist");
        }
    }
}
