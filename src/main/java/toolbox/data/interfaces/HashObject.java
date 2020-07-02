package toolbox.data.interfaces;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class HashObject<SELF extends HashObject, Key> {
  private final Map<String, Object> values;

  public SELF set(Key key, Object value) {
    this.values.put(this.toString(key), value);
    return (SELF) this;
  }

  protected String toString(Key key) {
    return key.toString();
  }

  public SELF set(Map<String, Object> values) {
    this.values.putAll(values);
    return (SELF) this;
  }

  public Optional<Object> getObject(Key key) {
    return Optional.ofNullable(this.get(key));
  }

  public Optional<Boolean> getBoolean(Key key) {
    return Optional.ofNullable(this.get(key));
  }

  public Optional<Number> getNumber(Key key) {
    Object value = this.get(key);

    if (value == null) {
      return Optional.empty();
    }

    if (value instanceof String) {
      return Optional.of(Integer.valueOf((String) value));
    }

    return Optional.of((Number) value);
  }

  public Optional<String> getString(Key key) {
    Object value = this.get(key);

    if (value == null) {
      return Optional.empty();
    }

    if (value instanceof Number) {
      String toStringValue = String.valueOf(((Number) value).intValue());
      return Optional.of(toStringValue);
    }

    return Optional.of(value.toString());
  }

  public Integer size() {
    return this.values.size();
  }

  public Map<String, Object> toMap() {
    return new HashMap<>(this.values);
  }

  public String toString() {
    return this.values.toString();
  }

  @SuppressWarnings("unchecked")
  private <T> T get(Key key) {
    return (T) this.values.get(this.toString(key));
  }
}
