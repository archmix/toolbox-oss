package toolbox.data.interfaces;

import java.util.HashMap;
import java.util.Map;

public class DataObject extends HashObject<DataObject, String> {
  protected DataObject() {
    this(new HashMap<>());
  }

  protected DataObject(Map<String, Object> values) {
    super(values);
  }

  public static DataObject create(Map<String, Object> configMap) {
    return new DataObject(configMap);
  }

  public static DataObject create() {
    return new DataObject();
  }
}