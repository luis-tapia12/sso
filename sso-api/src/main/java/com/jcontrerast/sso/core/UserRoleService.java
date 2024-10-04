package com.jcontrerast.sso.core;

import com.jcontrerast.sso.model.UserRole;
import com.jcontrerast.sso.model.id.UserRoleId;
import com.jcontrerast.utils.dto.PageFilterDTO;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface UserRoleService {
    Page<UserRole> getAllByUserId(UUID userId, PageFilterDTO filter);

    Page<UserRole> getAllByRoleId(UUID roleId, PageFilterDTO filter);

    UserRole create(UserRole userRole);

    UserRole update(UserRole userRole);

    void delete(UserRoleId id);
}
