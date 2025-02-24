package com.clem.taskmanager.exception;

import com.clem.taskmanager.shared.StatusEnum;
import com.clem.taskmanager.validator.ValidStatusEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.EnumSet;

public class StatusEnumException implements ConstraintValidator<ValidStatusEnum, StatusEnum> {
    @Override
    public boolean isValid(StatusEnum value, ConstraintValidatorContext context) {
        return value != null && EnumSet.allOf(StatusEnum.class).contains(value);
    }
}
