package toolbox.resources.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class ResourceFactory {
  private static final Logger LOG = LoggerFactory.getLogger(ResourceFactory.class);

  public static byte[] readBytes(InputStream inputStream) {
    try {
      byte[] buffer = new byte[inputStream.available()];
      inputStream.read(buffer);
      return buffer;
    } catch (IOException e) {
      LOG.warn("Cannot read resource stream", e);
      throw new IllegalStateException(e);
    }
  }

  public static InputStream stream(ResourcePath path, ResourceName resourceName) {
    String resourceLocation = path.pathTo(resourceName);
    return ResourceFactory.class.getClassLoader().getResourceAsStream(resourceLocation);
  }

}
