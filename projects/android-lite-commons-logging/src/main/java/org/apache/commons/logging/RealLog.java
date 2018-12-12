package org.apache.commons.logging;

class RealLog implements Log {

  private final String tag;

  RealLog(String tag) {
    this.tag = tag;
  }

  @Override
  public void info(Object message) {
    android.util.Log.i(tag, String.valueOf(message));
  }

  @Override
  public void warn(Object message) {
    android.util.Log.w(tag, String.valueOf(message));
  }

  @Override
  public void warn(Object message, Throwable t) {
    android.util.Log.w(tag, String.valueOf(message), t);
  }

  @Override
  public void error(Object message) {
    android.util.Log.e(tag, String.valueOf(message));
  }

  @Override
  public void error(Object message, Throwable t) {
    android.util.Log.e(tag, String.valueOf(message), t);
  }
}
