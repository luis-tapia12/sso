package com.jcontrerast.sso.enumeration.converter;

import com.jcontrerast.sso.enumeration.Language;
import jakarta.persistence.AttributeConverter;

public class LanguageConverter implements AttributeConverter<Language, String> {
    @Override
    public String convertToDatabaseColumn(Language language) {
        return language != null ? language.getId() : null;
    }

    @Override
    public Language convertToEntityAttribute(String value) {
        return value != null ? Language.fromId(value) : null;
    }
}
