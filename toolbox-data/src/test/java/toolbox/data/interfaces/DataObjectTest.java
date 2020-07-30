package toolbox.data.interfaces;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class DataObjectTest {
  @Test
  public void givenMapWhenGetThenReturnNumber(){
    Map<String, Object> values = new HashMap<>();
    values.put("A", 1);
    values.put("B", "1");
    values.put("C", "1.0");
    values.put("D", new BigDecimal("1.0"));


    DataObject dataObject = DataObject.create(values);
    this.doAssertion(dataObject);
  }

  @Test
  public void givenMapWhenPopThenReturnNumberAndRemoveFromInstance(){
    Map<String, Object> values = new HashMap<>();
    values.put("A", 1);
    values.put("B", "1");
    values.put("C", "1.0");
    values.put("D", new BigDecimal("1.0"));


    DataObject dataObject = DataObject.create(values);
    this.doAssertion(dataObject);

    Assert.assertFalse(dataObject.popNumber("A").isPresent());
    Assert.assertFalse(dataObject.popNumber("B").isPresent());
    Assert.assertFalse(dataObject.popNumber("C").isPresent());
    Assert.assertFalse(dataObject.popNumber("D").isPresent());
  }

  private void doAssertion(DataObject dataObject) {
    Assert.assertTrue(dataObject.popNumber("A").isPresent());
    Assert.assertTrue(dataObject.popNumber("B").isPresent());
    Assert.assertTrue(dataObject.popNumber("C").isPresent());
    Assert.assertTrue(dataObject.popNumber("D").isPresent());
  }

}
