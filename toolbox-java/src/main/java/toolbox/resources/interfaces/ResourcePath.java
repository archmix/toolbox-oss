package toolbox.resources.interfaces;

import lombok.RequiredArgsConstructor;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collectors;

@RequiredArgsConstructor(staticName = "create")
public class ResourcePath {
  private final String path;

  public static ResourcePath create(String... paths) {
    return ResourcePath.create(toPath(paths));
  }

  public static ResourcePath create(Path path) {
    return ResourcePath.create(toPath(path.toString()));
  }

  public String path() {
    return this.path;
  }

  public String pathTo(ResourceName resourceName) {
    return toPath(this.path, resourceName.value());
  }

  public String toString() {
    return this.path;
  }

  private static String toPath(String... paths) {
    String path = Arrays.asList(paths).stream().map(it -> it.replace("\\", "/")).collect(Collectors.joining("/"));
    if (path.startsWith("/")) {
      return path.substring(1);
    }
    return path;
  }
}
