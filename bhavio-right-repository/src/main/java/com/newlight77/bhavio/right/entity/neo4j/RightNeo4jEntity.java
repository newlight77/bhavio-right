package com.newlight77.bhavio.right.entity.neo4j;

import com.newlight77.bhavio.right.model.Right;
import com.newlight77.bhavio.right.model.TemporaryRight;
import lombok.*;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.id.UuidStrategy;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString
@EqualsAndHashCode
@NodeEntity(label = "Right")
public class RightNeo4jEntity implements Serializable{

  private static final long serialVersionUID = 2196530990380331933L;

  @Id
  @GeneratedValue(strategy = UuidStrategy.class)
  private String uid;
  @Index
  private String primary;
  @Index
  private String secondary;
  private Set<Right> rights;
  private Set<TemporaryRight> tempRights;
  private Instant modificationDate;

}
