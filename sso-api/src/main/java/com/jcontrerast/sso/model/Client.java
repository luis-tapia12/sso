package com.jcontrerast.sso.model;

import com.jcontrerast.sso.utils.Constants;
import com.jcontrerast.sso.validation.Create;
import com.jcontrerast.sso.validation.NullableNotBlank;
import com.jcontrerast.sso.validation.Update;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "SSO_CLIENTS")
public class Client {
    @Id
    @Column(name = "ID")
    private String id;

    @NotBlank(groups = Create.class)
    @NullableNotBlank(groups = Update.class)
    @Size(min = 4, max = 50)
    @Column(name = "NAME")
    private String name;

    @NotBlank(groups = Create.class)
    @NullableNotBlank(groups = Update.class)
    @Pattern(regexp = Constants.USER_NAME_REGEX)
    @Size(min = 4, max = 50)
    @Column(name = "CLIENT_ID")
    private String clientId;

    @NotBlank(groups = Create.class)
    @NullableNotBlank(groups = Update.class)
    @Size(min = 2, max = 50)
    @Column(name = "PASSWORD")
    private String password;

    @URL
    @NotNull(groups = Create.class)
    @NullableNotBlank(groups = Update.class)
    @Size(max = 100)
    @Column(name = "REDIRECT_URL")
    private String redirectUrl;

    @NullableNotBlank
    @Size(max = 100)
    @Column(name = "LOGO")
    private String logo;

    public void generateId() {
        this.id = UUID.randomUUID().toString();
    }
}
