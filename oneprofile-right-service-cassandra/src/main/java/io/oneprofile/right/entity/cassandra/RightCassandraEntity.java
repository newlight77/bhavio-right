package io.oneprofile.right.entity.cassandra;

import io.oneprofile.right.model.Right;
import io.oneprofile.right.model.TemporaryRight;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Indexed;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString
@EqualsAndHashCode
@Table(value = "RIGHT")
public class RightCassandraEntity implements Serializable{

  private static final long serialVersionUID = 2196530990380331933L;

  @Id
  @PrimaryKey
  private String uid;
  @Indexed
  private String primary;
  @Indexed
  private String secondary;
  private Set<Right> rights;
  private Set<TemporaryRight> tempRights;
  private Instant modificationDate;

}
