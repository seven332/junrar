package org.apache.commons.logging;

public class LogFactory {

  public static Log getLog(Class clazz) {
    return getLog(clazz.getSimpleName());
  }

  public static Log getLog(String name) {
    return new RealLog(name);
  }
}
