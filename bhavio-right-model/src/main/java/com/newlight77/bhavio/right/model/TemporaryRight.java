package com.newlight77.bhavio.right.model;

import lombok.*;

import java.time.Instant;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class TemporaryRight {

  private Right right;
  private Instant creationDate;
  private int duration;

  public TemporaryRight() {
    creationDate = Instant.now();
    duration = 0;
  }
}
