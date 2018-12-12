package com.github.junrar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.junit.AfterClass;

public class BaseTestCase {

  private static boolean isAndroid;

  private static final List<File> tempCopies = new ArrayList<>();

  static {
    try {
      Class.forName("android.app.Activity");
      isAndroid = true;
    } catch (ClassNotFoundException e) {
      isAndroid = false;
    }
  }

  public static File getResource(Class<?> clazz, final String path)
      throws IOException, URISyntaxException {
    if (isAndroid) {
      File of = File.createTempFile("copyFile", new File(path).getName());

      try (InputStream is = clazz.getResourceAsStream(path);
          OutputStream os = new FileOutputStream(of)) {
        IOUtils.copy(is, os);
      }

      synchronized (tempCopies) {
        tempCopies.add(of);
      }

      return of;
    } else {
      return new File(clazz.getResource(path).toURI());
    }
  }

  @AfterClass
  public static void clearTempCopies() {
    synchronized (tempCopies) {
      for (File f : tempCopies) {
        delete(f);
      }
      tempCopies.clear();
    }
  }

  private static void delete(File file) {
    if (file == null) {
      return;
    }

    File[] files = file.listFiles();
    if (files != null) {
      for (File f: files) {
        delete(f);
      }
    }

    file.delete();
  }
}
