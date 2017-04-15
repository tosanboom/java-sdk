package com.tosanboom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.io.IOException;
import java.util.TimeZone;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

public class Json {
    private static final ObjectMapper mapper = getObjectMapper();

    private static ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.setDateFormat(ISO8601DateFormat.getDateInstance());
        mapper.setTimeZone(TimeZone.getTimeZone("UTC"));
        mapper.setVisibility(mapper.getSerializationConfig().getDefaultVisibilityChecker().withFieldVisibility(ANY));

        return mapper;
    }

    public static RequestBody of(Object pojo) {
        MediaType jsonType = MediaType.parse("application/json;charset=UTF-8");

        try {
            return RequestBody.create(jsonType, mapper.writeValueAsString(pojo));
        } catch (JsonProcessingException e) {
            return RequestBody.create(jsonType, "{}");
        }
    }

    public static <T> T read(String jsonStr, Class<T> clazz) {
        try {
            return mapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            return null;
        }
    }
}