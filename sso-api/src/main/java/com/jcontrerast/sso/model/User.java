package com.jcontrerast.sso.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jcontrerast.sso.utils.Constants;
import com.jcontrerast.sso.validation.Create;
import com.jcontrerast.sso.validation.NullableNotBlank;
import com.jcontrerast.sso.validation.Update;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "SSO_USERS")
public class User {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "GENDER")
    private Character gender;

    @NotBlank(groups = Create.class)
    @NullableNotBlank(groups = Update.class)
    @Size(min = 2, max = 50)
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotBlank(groups = Create.class)
    @NullableNotBlank(groups = Update.class)
    @Size(min = 2, max = 100)
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotBlank(groups = Create.class)
    @NullableNotBlank(groups = Update.class)
    @Pattern(regexp = Constants.USER_NAME_REGEX)
    @Size(min = 4, max = 50)
    @Column(name = "USER_NAME")
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(groups = Create.class)
    @NullableNotBlank(groups = Update.class)
    @Size(min = 2, max = 100)
    @Column(name = "PASSWORD")
    private String password;

    @Past
    @NotNull(groups = Create.class)
    @Column(name = "DATE_OF_BIRTH")
    private LocalDateTime dateOfBirth;

    @NotBlank(groups = Create.class)
    @NullableNotBlank(groups = Update.class)
    @Size(min = 10, max = 15)
    @Column(name = "PHONE")
    private String phone;

    @NotBlank(groups = Create.class)
    @NullableNotBlank(groups = Update.class)
    @Size(min = 2, max = 50)
    @Column(name = "CITY")
    private String city;

    @NullableNotBlank
    @Size(max = 100)
    @Column(name = "PROFILE_PICTURE")
    private String profilePicture;

    public void generateId() {
        this.id = UUID.randomUUID().toString();
    }
}
