package com.jcontrerast.sso.service;

import com.jcontrerast.sso.core.UserService;
import com.jcontrerast.sso.model.User;
import com.jcontrerast.sso.repository.UserRepository;
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
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<User> getAll(PageFilterDTO filter) {
        Pageable pageable = Utils.getPageable(filter);

        return repository.findAll(pageable);
    }

    @Override
    public User getById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("The user with id " + id + " does not exist"));
    }

    @Override
    public User create(User user) {
        user.setId(UUID.randomUUID());
        if (!repository.existsById(user.getId())) {
            return repository.save(user);
        } else {
            throw new ResourceAlreadyExistsException("The user with id " + user.getId() + " already exists");
        }
    }

    @Override
    public User update(User user) {
        Assertions.isNotNull(user.getId());
        Optional<User> source = repository.findById(user.getId());

        if (source.isPresent()) {
            Utils.copyNotNull(user, source.get());
            return repository.save(source.get());
        } else {
            throw new ResourceNotFoundException("The user with id " + user.getId() + " does not exist");
        }
    }

    @Override
    public void delete(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("The user with id " + id + " does not exist");
        }
    }

    @Override
    public void updatePassword(UUID id, String newPassword) {
        User user = getById(id);

        user.setPassword(newPassword);
        repository.save(user);
    }
}
