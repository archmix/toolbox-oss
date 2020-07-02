package toolbox.validation.interfaces;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.function.Predicate;

public class ValueValidation<Value> {
  private Optional<Value> target;

  private String message;

  private ValueValidation(Value value, Predicate<Value> predicate, String message) {
    this.target = Optional.ofNullable(value).filter(predicate);
    this.message = message;
  }

  public static <T> ValueValidation<T> of(T value, Predicate<T> predicate) {
    return new ValueValidation<>(value, predicate, "");
  }

  public static ValueValidation<String> notEmpty(String value) {
    return new ValueValidation<>(value, notEmpty(), "Value cannot be empty.");
  }

  public static ValueValidation<Number> notZero(Number value) {
    return new ValueValidation<>(value, notZero(), "Value cannot be zero.");
  }

  public static <T> ValueValidation<T> notNull(T value) {
    return new ValueValidation<>(value, notNull(), "Value cannot be null.");
  }

  static Predicate<Number> notZero() {
    return ((value) -> value != null && value.intValue() != 0);
  }

  static Predicate<String> notEmpty() {
    return ((value) -> value != null && !value.isEmpty());
  }

  static <T> Predicate<T> notNull() {
    return ((value) -> value != null);
  }

  public ValueValidation<Value> withMessage(String messsage, Object... args) {
    String validationMessage = MessageFormat.format(message, args);
    this.message = validationMessage;
    return this;
  }

  public Value get() {
    return this.target.orElseThrow(() -> new IllegalStateException(this.message));
  }
}
