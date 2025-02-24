package com.clem.taskmanager.shared;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class StatusEnumDeserializer extends JsonDeserializer<StatusEnum> {

    @Override
    public StatusEnum deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        int value = jsonParser.getIntValue();
        return StatusEnum.valueToEnum(value);
    }
}
