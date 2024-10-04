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
@Table(name = "roles", schema = "sso")
public class Role {
    @Id
    @Column(name = "id", nullable = false)
    @NotNull(groups = Update.class)
    private UUID id;

    @NotNull(groups = Create.class)
    @Null(groups = Update.class)
    @Column(name = "application_id", nullable = false)
    private UUID applicationId;

    @Size(max = 50)
    @NotBlank(groups = Create.class)
    @NullableNotBlank(groups = Update.class)
    @Column(name = "name", nullable = false, length = 50)
    private String name;
}