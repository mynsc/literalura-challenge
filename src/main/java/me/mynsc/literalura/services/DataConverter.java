package me.mynsc.literalura.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConverter implements IDataConverter {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> classT) {
        try {
            return objectMapper.readValue(json, classT);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}