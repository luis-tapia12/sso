package com.jcontrerast.sso.repository;

import com.jcontrerast.sso.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
