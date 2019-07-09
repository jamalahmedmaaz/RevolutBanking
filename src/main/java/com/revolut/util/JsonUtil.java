package com.revolut.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * The type Json util.
 */
public final class JsonUtil {

    /**
     * The constant OBJECT_MAPPER.
     */
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JsonUtil() {
    }

    /**
     * As json json node.
     *
     * @param content the content
     * @return the json node
     */
    public static JsonNode asJson(String content) {
        try {
            JsonNode exportDataNode =
                    OBJECT_MAPPER.readTree(content);
            return exportDataNode;
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }

    /**
     * To json string string.
     *
     * @param object the object
     * @return the string
     */
    public static String toJsonString(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    /**
     * Read object t.
     *
     * @param <T>   the type parameter
     * @param json  the json
     * @param clazz the clazz
     * @return the t
     */
    public static <T> T readObject(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    /**
     * Pretty string.
     *
     * @param object the object
     * @return the string
     * @throws IOException the io exception
     */
    public static String pretty(Object object) throws IOException {
        return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }
}
