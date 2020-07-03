package toolbox.resources.interfaces;

import java.net.URL;

public interface ResourceResolver {

  public static ResourceResolverDecorator resolver(URL location) {
    ResourceResolver resolver = RegularResourceResolver.valueOf();
    if (JarResourceResolver.valueOf().accept(location)) {
      resolver = JarResourceResolver.valueOf();
    }
    return ResourceResolverDecorator.create(location, resolver);
  }

  boolean accept(URL location);

  Iterable<ResourceName> resolve(URL location);
}

