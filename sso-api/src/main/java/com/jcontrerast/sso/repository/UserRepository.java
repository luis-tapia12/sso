package com.jcontrerast.sso.repository;

import com.jcontrerast.sso.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUserName(String userName);

    @Query("""
            SELECT p.name
            FROM Permission p
            INNER JOIN Authorization a ON p.roleId = a.roleId
            INNER JOIN Role r ON a.roleId = r.id
            WHERE a.userId = :userId
            AND r.applicationId = :applicationId
            """)
    List<String> findAllPermissions(UUID userId, UUID applicationId);
}
