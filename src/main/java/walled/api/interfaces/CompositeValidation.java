package walled.api.interfaces;

import java.util.ArrayList;
import java.util.List;

public class CompositeValidation {
  private List<ValueValidation<?>> validations;

  private CompositeValidation() {
    this.validations = new ArrayList<>();
  }

  public static CompositeValidation create() {
    return new CompositeValidation();
  }

  public CompositeValidation add(ValueValidation<?> validation) {
    this.validations.add(validation);
    return this;
  }

  public void validate() {
    this.validations.forEach(ValueValidation::get);
  }
}
