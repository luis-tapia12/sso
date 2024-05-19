package com.jcontrerast.sso.service.impl;

import com.jcontrerast.sso.dto.PageDTO;
import com.jcontrerast.sso.model.User;
import com.jcontrerast.sso.repository.UserRepository;
import com.jcontrerast.sso.service.UserService;
import com.jcontrerast.sso.utils.Utils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<User> getUsers(PageDTO pageDTO) {
        return repository.findAll(Utils.getPageable(pageDTO));
    }

    @Override
    public User getUser(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("The user with id " + id + " does not exist"));
    }

    @Override
    public User saveUser(User user) {
        user.generateId();
        return repository.save(user);
    }

    @Override
    public User updateUser(User user) {
        User currentUser = getUser(user.getId());
        Utils.copyNonNull(user, currentUser);
        return repository.save(currentUser);
    }

    @Override
    public void deleteUser(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("The user with id " + id + " does not exist");
        }
    }
}
