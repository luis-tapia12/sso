package com.jcontrerast.sso.service.impl;

import com.jcontrerast.sso.dto.PageDTO;
import com.jcontrerast.sso.model.User;
import com.jcontrerast.sso.repository.UserRepository;
import com.jcontrerast.sso.service.UserService;
import com.jcontrerast.sso.utils.Utils;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public User updateUser(User user) {
        User currentUser = getUser(user.getId());
        Utils.copyNonNull(user, currentUser);
        if (user.getPassword() != null) {
            currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("The user username " + username + " does not exist"));
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getId())
                .password(user.getPassword())
                .build();
    }
}
