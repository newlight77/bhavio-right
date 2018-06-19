package com.newlight77.bhavio.right.model;

public enum Right {

  READ("read"),
  WRITE("write"),
  DELETE("delete");

  private final String value;

  Right(final String value) {
    this.value = value;
  }

  public static Right fromValue(String value) {
    for (Right right : values()) {
      if (value.equals(right.getValue())) {
        return right;
      }
    }
    throw new IllegalArgumentException(value);
  }

  public String getValue() {
    return value;
  }
}
