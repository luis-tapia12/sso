package com.jcontrerast.sso.repository;

import com.jcontrerast.sso.model.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {
    Page<Permission> findByRoleId(UUID roleId, Pageable pageable);
}
