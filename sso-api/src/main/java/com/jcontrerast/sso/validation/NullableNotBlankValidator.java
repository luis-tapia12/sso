package com.jcontrerast.sso.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NullableNotBlankValidator implements ConstraintValidator<NullableNotBlank, CharSequence> {
    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {
        if (charSequence == null) {
            return true;
        } else {
            return !charSequence.toString().isBlank();
        }
    }
}
