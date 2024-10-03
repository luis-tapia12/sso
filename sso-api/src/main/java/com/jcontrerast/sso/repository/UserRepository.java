package com.jcontrerast.sso.repository;

import com.jcontrerast.sso.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
