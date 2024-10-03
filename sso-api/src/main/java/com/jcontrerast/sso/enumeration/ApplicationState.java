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

    public static ApplicationState fromId(String id) {
        for (ApplicationState state : values()) {
            if (state.id.equals(id)) {
                return state;
            }
        }
        return null;
    }
}
