package com.newlight77.right.service;

import com.newlight77.right.model.Right;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class RightFilter implements Serializable {

  private static final long serialVersionUID = 8216682352847654880L;

  private String primary;
  private String secondary;
  private Collection<Right> rights = new HashSet<>();

}
