package com.newlight77.right.demo.model;

//import com.newlight77.right.model.Right;
//import com.newlight77.right.model.TemporaryRight;
import lombok.*;

import java.util.Set;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class RightsModel {
  private String primary;
  private String secondary;
//  private Set<Right> rights;
//  private Set<TemporaryRight> tempRights;
}
