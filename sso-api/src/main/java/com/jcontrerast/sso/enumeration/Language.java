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

    public static Language fromId(String id) {
        for (Language lang : values()) {
            if (lang.id.equals(id)) {
                return lang;
            }
        }
        return null;
    }
}
