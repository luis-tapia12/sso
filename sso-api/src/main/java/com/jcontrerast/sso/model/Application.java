package com.jcontrerast.sso.model;

import com.jcontrerast.sso.enumeration.ApplicationState;
import com.jcontrerast.sso.enumeration.converter.ApplicationStateConverter;
import com.jcontrerast.sso.utils.Constants;
import com.jcontrerast.utils.validation.Create;
import com.jcontrerast.utils.validation.NullableNotBlank;
import com.jcontrerast.utils.validation.Update;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "applications", schema = "sso")
public class Application {
    @Id
    @Column(name = "id", nullable = false)
    @NotNull(groups = Update.class)
    private UUID id;

    @Size(max = 50)
    @NotBlank(groups = Create.class)
    @NullableNotBlank(groups = Update.class)
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "state", length = Integer.MAX_VALUE)
    @Convert(converter = ApplicationStateConverter.class)
    private ApplicationState state;

    @Size(max = 255)
    @Column(name = "description")
    @NullableNotBlank
    private String description;

    @Size(max = 50)
    @NotBlank(groups = Create.class)
    @NullableNotBlank(groups = Update.class)
    @Pattern(regexp = Constants.REGEX_USER_NAME)
    @Column(name = "client_id", nullable = false, length = 50)
    private String clientId;

    @Size(max = 100)
    @NotBlank(groups = Create.class)
    @NullableNotBlank(groups = Update.class)
    @Column(name = "client_secret", nullable = false, length = 100)
    private String clientSecret;

    @URL
    @Size(max = 100)
    @NotNull(groups = Create.class)
    @Column(name = "redirect_url", nullable = false, length = 100)
    private String redirectUrl;

    @URL
    @Size(max = 100)
    @Column(name = "logo_url", length = 100)
    private String logoUrl;
}