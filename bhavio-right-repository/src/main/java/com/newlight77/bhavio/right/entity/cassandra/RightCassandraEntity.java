package com.newlight77.bhavio.right.entity.cassandra;

import com.newlight77.bhavio.right.model.Right;
import com.newlight77.bhavio.right.model.TemporaryRight;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Indexed;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.persistence.GeneratedValue;
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
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
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
