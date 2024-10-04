package com.jcontrerast.sso.repository;

import com.jcontrerast.sso.model.UserRole;
import com.jcontrerast.sso.model.id.UserRoleId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
    Page<UserRole> findByUserId(UUID userId, Pageable pageable);

    Page<UserRole> findByRoleId(UUID roleId, Pageable pageable);
}
