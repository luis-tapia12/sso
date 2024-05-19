package com.jcontrerast.sso.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "SSO_CLIENTS")
public class Client {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CLIENT_ID")
    private String clientId;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "REDIRECT_URL")
    private String redirectUrl;

    @Column(name = "LOGO")
    private String logo;

    public void generateId() {
        this.id = UUID.randomUUID().toString();
    }
}
