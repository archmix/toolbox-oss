package toolbox.json.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class Json {
  private static final ObjectMapper mapper = new ObjectMapper();
  private static final Logger LOG = LoggerFactory.getLogger(Json.class);

  static {
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.registerModule(new Jdk8Module());
    mapper.registerModule(new JavaTimeModule());
  }

  public static Optional<String> toJson(Object value) {
    try {
      return Optional.of(mapper.writeValueAsString(value));
    } catch (JsonProcessingException e) {
      LOG.warn("Error when trying to convert object to json", e);
      return Optional.empty();
    }
  }

  public static <T> Optional<T> fromJson(String json, Class<T> type) {
    try {
      return Optional.of(mapper.readValue(json, type));
    } catch (JsonProcessingException e) {
      LOG.warn("Error when trying to convert json to object", e);
      return Optional.empty();
    }
  }
}
