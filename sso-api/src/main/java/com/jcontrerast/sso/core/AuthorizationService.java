package com.jcontrerast.sso.core;

import com.jcontrerast.sso.model.Authorization;
import com.jcontrerast.sso.model.Role;
import com.jcontrerast.sso.model.User;
import com.jcontrerast.utils.dto.PageFilterDTO;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface AuthorizationService {
    Page<Role> getAllByUserId(UUID userId, PageFilterDTO filter);

    Page<User> getAllByRoleId(UUID roleId, PageFilterDTO filter);

    Authorization create(Authorization authorization);

    Authorization update(Authorization authorization);

    void delete(UUID id);
}
