package com.jcontrerast.sso.model;

import com.jcontrerast.utils.validation.Create;
import com.jcontrerast.utils.validation.Update;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "authorizations", schema = "sso")
public class Authorization {
    @Id
    @Column(name = "id")
    @NotNull(groups = Update.class)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    @NotNull(groups = Create.class)
    @Null(groups = Update.class)
    private UUID userId;

    @Column(name = "role_id", nullable = false)
    @NotNull(groups = Create.class)
    @Null(groups = Update.class)
    private UUID roleId;

    @Column(name = "enabled")
    @NotNull
    private Boolean enabled;
}