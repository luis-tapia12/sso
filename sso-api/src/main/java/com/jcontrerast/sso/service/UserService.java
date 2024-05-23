package com.jcontrerast.sso.service;

import com.jcontrerast.sso.dto.PageDTO;
import com.jcontrerast.sso.model.User;
import org.springframework.data.domain.Page;

public interface UserService {
    Page<User> getUsers(PageDTO pageDTO);

    User getUser(String id);

    User getUserByUsername(String username);

    User saveUser(User user);

    User updateUser(User user);

    void deleteUser(String id);
}
