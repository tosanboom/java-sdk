package com.tosanboom;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.io.IOException;
import java.util.TimeZone;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

/**
 * Utilities for JSON serialization/de-serialization
 *
 * @author ALi Dehghani
 */
public class Json {
    private static final ObjectMapper mapper = getObjectMapper();
    private static final MediaType JSON_TYPE =  MediaType.parse("application/json;charset=UTF-8");

    private static ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setDateFormat(ISO8601DateFormat.getDateInstance());
        mapper.setTimeZone(TimeZone.getTimeZone("UTC"));
        mapper.setVisibility(mapper.getSerializationConfig().getDefaultVisibilityChecker().withFieldVisibility(ANY));

        return mapper;
    }

    /**
     * Create a JSON {@linkplain RequestBody} from the passed pojo
     *
     * @param pojo The pojo to convert to a JSON string
     * @return A {@linkplain RequestBody} wrapping the jsonified pojo as its request body
     * with a proper {@code Content-Type}
     * @throws JsonException When something went wrong with json serialization
     */
    public static RequestBody of(Object pojo) {
        try {
            return RequestBody.create(JSON_TYPE, mapper.writeValueAsString(pojo));
        } catch (JsonProcessingException e) {
            throw new JsonException("Couldn't generate a JSON string from " + pojo, e);
        }
    }

    /**
     * Convert the json string to a class specified by the given {@code clazz} parameter
     *
     * @param jsonStr String representation of a json
     * @param clazz Encapsulates type information of the destination pojo
     * @param <T> Type of the destination pojo
     * @return An instance of {@linkplain T} corresponding to the given json string
     * @throws JsonException When something went wrong with json de-serialization process
     */
    public static <T> T read(String jsonStr, Class<T> clazz) {
        try {
            return mapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            throw new JsonException("Couldn't map the " + jsonStr + " to the " + clazz + " type", e);
        }
    }
}