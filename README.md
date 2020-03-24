# How to use Walleed

Import dependency to your maven pom
```xml
<dependency>
  <groupId>org.archmix</groupId>
  <artifactId>walleed</artifactId>
  <version>${walleedVersion}</version>
</dependency>
```
# Example of Walled API

``` java
import walled.api.interfaces.CompositeValidation;

import static walled.api.interfaces.ValueValidation.*;

public void validate() {
  CompositeValidation.create().add(notEmpty(this.url).withMessage("Invalid database url"))
    .add(notEmpty(this.user).withMessage("Invalid database user"))
    .add(notNull(this.vendor).withMessage("Database is not supported yet")).validate();
}
```

# License
https://github.com/archmix/community/blob/master/LICENSE.md

# CODE OF CONDUCT
https://github.com/archmix/community/blob/master/CODE_OF_CONDUCT.md
