package com.jcontrerast.sso.core;

import com.jcontrerast.sso.model.Permission;
import com.jcontrerast.utils.dto.PageFilterDTO;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface PermissionService {
    Page<Permission> getAll(PageFilterDTO filter);

    Page<Permission> getAllByRoleId(UUID roleId, PageFilterDTO filter);

    Permission create(Permission permission);

    Permission update(Permission permission);

    void delete(UUID id);
}
