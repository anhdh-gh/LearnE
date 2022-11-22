package source.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;

public class JsonUtil {

    /**
     * Instantiates a new json util.
     */
    private JsonUtil() {
    }

    /**
     * Gets the generic object.
     *
     * @param <T>   the generic type
     * @param input the input
     * @param clazz the clazz
     * @return the generic object
     */
    public static <T> T getGenericObject(Object input, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return objectMapper.convertValue(input, clazz);
    }

    /**
     * Convert json str to object.
     *
     * @param <T>   the generic type
     * @param json  the json
     * @param clazz the clazz
     * @return the t
     */
    public static <T> T convertJsonStrToObject(String json, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }

    /**
     * Convert object to string.
     *
     * @param obj the obj
     * @return the string
     * @throws JsonProcessingException the json processing exception
     */
    public static String convertObjectToString(Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }

    /**
     * Read Object
     *
     * @param input the string
     * @param clazz the clazz
     * @return the t
     * @throws JsonProcessingException the json processing exception
     * @throws JsonMappingException    the json mapping exception
     * @throws IOException             the io exception
     */
    public static <T> T readObject(String input, Class<T> clazz)
            throws JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(input, clazz);
    }
}
