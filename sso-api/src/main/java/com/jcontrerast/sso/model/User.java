package com.jcontrerast.sso.model;

import com.jcontrerast.sso.enumeration.Language;
import com.jcontrerast.sso.enumeration.converter.LanguageConverter;
import com.jcontrerast.sso.utils.Constants;
import com.jcontrerast.utils.validation.Create;
import com.jcontrerast.utils.validation.NullableNotBlank;
import com.jcontrerast.utils.validation.Update;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users", schema = "sso")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @NotNull(groups = Update.class)
    private UUID id;

    @Size(max = 50)
    @NotBlank(groups = Create.class)
    @NullableNotBlank(groups = Update.class)
    @Pattern(regexp = Constants.REGEX_NAME)
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Size(max = 100)
    @NotBlank(groups = Create.class)
    @NullableNotBlank(groups = Update.class)
    @Pattern(regexp = Constants.REGEX_NAME)
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Size(max = 50)
    @NotBlank(groups = Create.class)
    @NullableNotBlank(groups = Update.class)
    @Pattern(regexp = Constants.REGEX_USER_NAME)
    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    @Size(max = 100)
    @NotBlank(groups = Create.class)
    @Null(groups = Update.class)
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @URL
    @Size(max = 100)
    @Column(name = "profile_picture_url", length = 100)
    private String profilePictureUrl;

    @Column(name = "language", length = 2)
    @Convert(converter = LanguageConverter.class)
    private Language language;
}