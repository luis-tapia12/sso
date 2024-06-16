package com.jcontrerast.sso.repository;

import com.jcontrerast.sso.model.GroupUser;
import com.jcontrerast.sso.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface GroupUserRepository extends JpaRepository<GroupUser, String> {
    @Query("SELECT u FROM User u JOIN GroupUser g ON g.userId = u.id WHERE g.groupId = :groupId")
    Page<User> findByGroupId(String groupId, Pageable pageable);

    boolean existsByUserIdAndGroupId(String userId, String groupId);

    @Transactional
    void deleteByUserIdAndGroupId(String userId, String groupId);
}
