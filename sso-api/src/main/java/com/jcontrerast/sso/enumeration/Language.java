package com.jcontrerast.sso.enumeration;

import lombok.Getter;

@Getter
public enum Language {
    ENGLISH("en"),
    SPANISH("es");

    private final String id;

    private Language(String id) {
        this.id = id;
    }
}
