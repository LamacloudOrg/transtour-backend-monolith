package com.transtour.travel.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;

public class TravelConverter implements AttributeConverter<TravelInfoPayload, String> {

    private final static ObjectMapper GSON = new ObjectMapper();

    @Override
    @SneakyThrows
    public String convertToDatabaseColumn(TravelInfoPayload mjo) {
        return GSON.writeValueAsString(mjo);
    }

    @Override
    @SneakyThrows
    public TravelInfoPayload convertToEntityAttribute(String dbData) {
        return GSON.readValue(dbData, TravelInfoPayload.class);
    }
}