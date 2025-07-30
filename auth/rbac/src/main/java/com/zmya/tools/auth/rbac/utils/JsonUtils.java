package com.zmya.tools.auth.rbac.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;

public class JsonUtils {

    public static final ObjectMapper OBJECT_MAPPER = Jackson2ObjectMapperBuilder.json()
            .modules(new JavaTimeModule())
            .featuresToDisable(
                    SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
            )
            .build();

    private JsonUtils() {
    }

    public static String toPrettyJson(Object obj) {
        try {
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new JacksonException("convert to json failed", e);
        }
    }

    public static String toJson(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new JacksonException("convert to json failed", e);
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            throw new JacksonException("parse json failed", e);
        }
    }

    public static <T> T fromJson(String json, TypeReference<T> typeRef) {
        try {
            return OBJECT_MAPPER.readValue(json, typeRef);
        } catch (IOException e) {
            throw new JacksonException("parse json failed", e);
        }
    }

    public static JsonNode toJsonNode(String json) {
        try {
            return OBJECT_MAPPER.readTree(json);
        } catch (IOException e) {
            throw new JacksonException("parse json failed", e);
        }
    }

    public static <T> T deepCopy(T obj, Class<T> clazz) {
        return fromJson(toJson(obj), clazz);
    }

    public static class JacksonException extends RuntimeException {
        public JacksonException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
