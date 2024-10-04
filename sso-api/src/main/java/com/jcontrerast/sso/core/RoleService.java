package com.jcontrerast.sso.core;

import com.jcontrerast.sso.model.Role;
import com.jcontrerast.utils.dto.PageFilterDTO;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface RoleService {
    Page<Role> getAll(PageFilterDTO filter);

    Page<Role> getAllByApplicationId(UUID applicationId, PageFilterDTO filter);

    Role create(Role role);

    Role update(Role role);

    void delete(UUID id);
}
