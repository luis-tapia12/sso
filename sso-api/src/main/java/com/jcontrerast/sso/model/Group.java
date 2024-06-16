package com.jcontrerast.sso.model;

import com.jcontrerast.sso.validation.Create;
import com.jcontrerast.sso.validation.NullableNotBlank;
import com.jcontrerast.sso.validation.Update;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SSO_GROUPS")
public class Group {
    @Id
    @Column(name = "ID")
    private String id;

    @NotBlank(groups = Create.class)
    @NullableNotBlank(groups = Update.class)
    @Column(name = "NAME")
    private String name;

    @NullableNotBlank
    @Column(name = "DESCRIPTION")
    private String description;
}
