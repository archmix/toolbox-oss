# How to use Toolbox

Import dependency to your maven pom
```xml
<dependency>
  <groupId>org.archmix</groupId>
  <artifactId>toolbox</artifactId>
  <version>${toolboxVersion}</version>
</dependency>
```
# Example of Validation API

``` java
import toolbox.validation.interfaces.CompositeValidation;

import static toolbox.validation.interfaces.ValueValidation.*;

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
