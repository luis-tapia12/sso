package com.jcontrerast.sso.model;

import com.jcontrerast.sso.model.id.UserRoleId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user_roles", schema = "sso")
@IdClass(UserRoleId.class)
public class UserRole {
    @Id
    @Column(name = "user_id", nullable = false)
    @NotNull
    private UUID userId;

    @Id
    @Column(name = "role_id", nullable = false)
    @NotNull
    private UUID roleId;

    @Column(name = "enabled")
    @NotNull
    private Boolean enabled;
}