package com.transtour.travel.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

//@Converter(autoApply = true)
@Converter
public class TravelConverter implements AttributeConverter<TravelInfoPayload, String> {

    private static final ObjectMapper GSON = new ObjectMapper();

    static {
        GSON.registerModule(new JavaTimeModule());
        GSON.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    }

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(TravelInfoPayload mjo) {
        return GSON.writeValueAsString(mjo);
    }

    @Override
    @SneakyThrows
    public TravelInfoPayload convertToEntityAttribute(String dbData) {
        return GSON.readValue(dbData, TravelInfoPayload.class);
    }
}