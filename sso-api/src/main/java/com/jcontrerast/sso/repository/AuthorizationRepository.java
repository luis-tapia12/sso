package com.jcontrerast.sso.repository;

import com.jcontrerast.sso.model.Authorization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorizationRepository extends JpaRepository<Authorization, UUID> {
    Page<Authorization> findByUserId(UUID userId, Pageable pageable);

    Page<Authorization> findByRoleId(UUID roleId, Pageable pageable);
}
