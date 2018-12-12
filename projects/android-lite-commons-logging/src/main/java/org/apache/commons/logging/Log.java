package org.apache.commons.logging;

public interface Log {

  void info(Object message);

  void warn(Object message);

  void warn(Object message, Throwable t);

  void error(Object message);

  void error(Object message, Throwable t);
}
