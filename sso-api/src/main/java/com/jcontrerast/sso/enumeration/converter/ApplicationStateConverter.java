package com.jcontrerast.sso.enumeration.converter;

import com.jcontrerast.sso.enumeration.ApplicationState;
import jakarta.persistence.AttributeConverter;

public class ApplicationStateConverter implements AttributeConverter<ApplicationState, String> {
    @Override
    public String convertToDatabaseColumn(ApplicationState state) {
        return state != null ? state.getId() : null;
    }

    @Override
    public ApplicationState convertToEntityAttribute(String value) {
        return value != null ? ApplicationState.fromId(value) : null;
    }
}
