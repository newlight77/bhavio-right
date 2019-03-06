package io.oneprofile.right.demo.model;

import io.oneprofile.right.model.Right;
import io.oneprofile.right.model.TemporaryRight;
import io.oneprofile.right.model.Right;
import lombok.*;

import java.time.Instant;
import java.util.Set;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class RightDto {
  private String primary;
  private String secondary;
  private Set<Right> rights;
  private Set<TemporaryRight> tempRights;
  private Instant modificationDate;
}
