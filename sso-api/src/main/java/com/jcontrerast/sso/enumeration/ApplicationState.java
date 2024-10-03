package com.jcontrerast.sso.enumeration;

import lombok.Getter;

@Getter
public enum ApplicationState {
    DEVELOPMENT("D"),
    ACTIVE("A"),
    INACTIVE("I");

    private final String id;

    private ApplicationState(String id) {
        this.id = id;
    }
}
