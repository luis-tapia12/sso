package com.jcontrerast.sso.repository;

import com.jcontrerast.sso.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Page<Role> findByApplicationId(UUID applicationId, Pageable pageable);
}
