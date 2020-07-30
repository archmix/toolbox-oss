package toolbox.data.interfaces;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("unchecked")
public abstract class HashObject<SELF extends HashObject, Key> implements Serializable {
  private Map<String, Object> values;

  protected HashObject() {
    this(new HashMap<>());
  }

  protected HashObject(Map<String, Object> values) {
    this.values = values;
  }

  public SELF set(Key key, Object value) {
    this.values.put(key.toString(), value);
    return (SELF) this;
  }

  public SELF set(Map<String, Object> values) {
    for (String key : values.keySet()) {
      this.values.put(key, values.get(key));
    }
    return (SELF) this;
  }

  public Optional<Object> popObject(Key key) {
    return Optional.ofNullable(this.pop(key));
  }

  public Optional<Boolean> popBoolean(Key key) {
    return Optional.ofNullable(this.pop(key));
  }

  public Optional<Number> popNumber(Key key) {
    Optional<Number> value = this.getNumber(key);
    this.values.remove(key.toString());
    return value;
  }

  public Optional<String> popString(Key key) {
    Optional<String> value = this.getString(key);
    this.values.remove(key.toString());
    return value;
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
      try {
        return Optional.of(Double.valueOf((String) value));
      } catch (Exception e){
        return Optional.empty();
      }
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

  private <T> T get(Key key) {
    return (T) this.values.get(key.toString());
  }

  private <T> T pop(Key key) {
    T value = this.get(key);
    this.values.remove(key.toString());
    return value;
  }
}
