package toolbox.resources.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import toolbox.classloader.interfaces.CompositeClassLoader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Optional;

import static toolbox.resources.interfaces.ResourceFactory.*;

public class ResourceStream {
  private static final Logger LOG = LoggerFactory.getLogger(ResourceStream.class);
  private final ClassLoader classLoader;
  private final Charset charset;

  private ResourceStream(ClassLoader classLoader, Charset charset) {
    this.classLoader = classLoader;
    this.charset = charset;
  }

  public static ResourceStream create() {
    return create(defaultClassLoader());
  }

  public static ResourceStream create(Charset charset) {
    return create(defaultClassLoader(), charset);
  }

  public static ResourceStream create(ClassLoader classLoader) {
    return create(classLoader, StandardCharsets.UTF_8);
  }

  public static ResourceStream create(ClassLoader classLoader, Charset charset) {
    return new ResourceStream(classLoader, charset);
  }

  public Optional<String> read(Path path) {
    String resourceLocation = ResourcePath.create(path).path();
    return this.read(resourceLocation);
  }

  public Optional<String> read(ResourcePath path, ResourceName resourceName) {
    return this.read(path.pathTo(resourceName));
  }

  public String toString(byte[] bytes) {
    return new String(bytes, this.charset);
  }

  private Optional<String> read(String resourceLocation) {
    try (InputStream input = this.classLoader.getResourceAsStream(resourceLocation)) {
      if(input == null){
        return Optional.empty();
      }
      byte[] buffer = readBytes(input);
      return Optional.of(new String(buffer, this.charset));
    } catch (IOException e) {
      LOG.warn("Cannot close resource stream", e);
      return Optional.empty();
    }
  }

  private static CompositeClassLoader defaultClassLoader() {
    return CompositeClassLoader.create().join(ResourceStream.class.getClassLoader());
  }
}
