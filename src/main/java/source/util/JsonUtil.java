package source.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * The Class JsonUtil.
 */
public class JsonUtil {

  /**
   * Instantiates a new json util.
   */
  private JsonUtil() {}

  /**
   * Gets the generic object.
   *
   * @param <T> the generic type
   * @param input the input
   * @param clazz the clazz
   * @return the generic object
   */
  public static <T> T getGenericObject(Object input, Class<T> clazz) {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    return objectMapper.convertValue(input, clazz);
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

  public static <T> T readObject(String input, Class<T> clazz)
      throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return objectMapper.readValue(input, clazz);
  }
}