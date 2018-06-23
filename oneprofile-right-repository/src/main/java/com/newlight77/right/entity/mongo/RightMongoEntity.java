package com.newlight77.right.entity.mongo;

import com.newlight77.right.model.Right;
import com.newlight77.right.model.TemporaryRight;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class RightMongoEntity implements Serializable{

  private static final long serialVersionUID = 2196530990380331933L;

  @Id
  private String uid;
  @Indexed
  private String primary;
  @Indexed
  private String secondary;
  private Set<Right> rights;
  private Set<TemporaryRight> tempRights;
  private Instant modificationDate;

}
