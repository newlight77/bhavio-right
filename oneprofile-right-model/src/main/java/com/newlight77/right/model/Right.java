package com.newlight77.right.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Right {

  OWNER("owner"),
  ADMIN_READ("admin_read"),
  ADMIN_WRITE("admin_write"),
  ADMIN_DELETE("admin_delete");

  private final String value;

  Right(final String value) {
    this.value = value;
  }

  @JsonCreator
  public static Right value(String value) {
    for (Right right : values()) {
      if (value.equals(right.value())) {
        return right;
      }
    }
    throw new IllegalArgumentException(value);
  }

  @JsonValue
  public String value() {
    return value;
  }
}
