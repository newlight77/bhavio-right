package io.oneprofile.right.exception;

public class NoRightException extends RuntimeException {

  public static final int CODE = 403;

  public NoRightException(String message) {
    super(message);
  }

  public NoRightException(String message, Throwable throwable) {
    super(message, throwable);
  }

}
