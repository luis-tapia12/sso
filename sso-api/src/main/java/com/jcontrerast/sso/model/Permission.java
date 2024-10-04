package com.jcontrerast.sso.model;

import com.jcontrerast.utils.validation.Create;
import com.jcontrerast.utils.validation.NullableNotBlank;
import com.jcontrerast.utils.validation.Update;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "permissions", schema = "sso")
public class Permission {
    @Id
    @Column(name = "id", nullable = false)
    @NotNull(groups = Update.class)
    private UUID id;

    @NotNull(groups = Create.class)
    @Null(groups = Update.class)
    @Column(name = "role_id", nullable = false)
    private UUID roleId;

    @NotBlank(groups = Create.class)
    @NullableNotBlank(groups = Update.class)
    @Size(max = 100)
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Size(max = 255)
    @NullableNotBlank
    @Column(name = "description")
    private String description;
}