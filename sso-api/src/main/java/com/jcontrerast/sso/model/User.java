package com.jcontrerast.sso.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
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

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "USEN_NAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "DATE_OF_BIRTH")
    private LocalDateTime dateOfBirth;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "CITY")
    private String city;

    @Column(name = "PROFILE_PICTURE")
    private String profilePicture;

    public void generateId() {
        this.id = UUID.randomUUID().toString();
    }
}
