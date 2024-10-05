package com.jcontrerast.sso.repository;

import com.jcontrerast.sso.model.Authorization;
import com.jcontrerast.sso.model.Role;
import com.jcontrerast.sso.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface AuthorizationRepository extends JpaRepository<Authorization, UUID> {
    @Query("SELECT r FROM Role r WHERE r.id IN (SELECT a.roleId FROM Authorization a WHERE a.userId = :userId)")
    Page<Role> findByUserId(UUID userId, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.id IN (SELECT a.userId FROM Authorization a WHERE a.roleId = :roleId)")
    Page<User> findByRoleId(UUID roleId, Pageable pageable);
}
