package com.clem.taskmanager.shared;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<StatusEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(StatusEnum attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getStatus();
    }

    @Override
    public StatusEnum convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return StatusEnum.valueToEnum(dbData);
    }
}
