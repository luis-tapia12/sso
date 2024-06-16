package com.jcontrerast.sso.repository;

import com.jcontrerast.sso.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, String> {
}
