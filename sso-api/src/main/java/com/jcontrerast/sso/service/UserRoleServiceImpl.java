package com.jcontrerast.sso.service;

import com.jcontrerast.sso.core.UserRoleService;
import com.jcontrerast.sso.model.UserRole;
import com.jcontrerast.sso.model.id.UserRoleId;
import com.jcontrerast.sso.repository.UserRoleRepository;
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
public class UserRoleServiceImpl implements UserRoleService {
    public final UserRoleRepository repository;

    public UserRoleServiceImpl(UserRoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<UserRole> getAllByUserId(UUID userId, PageFilterDTO filter) {
        Pageable pageable = Utils.getPageable(filter);

        return repository.findByUserId(userId, pageable);
    }

    @Override
    public Page<UserRole> getAllByRoleId(UUID roleId, PageFilterDTO filter) {
        Pageable pageable = Utils.getPageable(filter);

        return repository.findByRoleId(roleId, pageable);
    }

    @Override
    public UserRole create(UserRole userRole) {
        UserRoleId id = new UserRoleId(userRole.getUserId(), userRole.getRoleId());
        if (!repository.existsById(id)) {
            return repository.save(userRole);
        } else {
            throw new ResourceAlreadyExistsException("The user role with id " + id + " already exists");
        }
    }

    @Override
    public UserRole update(UserRole userRole) {
        Assertions.isNotNull(userRole.getUserId());
        Assertions.isNotNull(userRole.getRoleId());
        UserRoleId id = new UserRoleId(userRole.getUserId(), userRole.getRoleId());
        Optional<UserRole> source = repository.findById(id);

        if (source.isPresent()) {
            Utils.copyNotNull(userRole, source.get());
            return repository.save(source.get());
        } else {
            throw new ResourceNotFoundException("The user role with id " + id + " does not exist");
        }
    }

    @Override
    public void delete(UserRoleId id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("The user role with id " + id + " does not exist");
        }
    }
}
