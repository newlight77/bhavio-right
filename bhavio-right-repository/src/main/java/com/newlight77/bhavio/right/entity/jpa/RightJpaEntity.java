package com.newlight77.bhavio.right.entity.jpa;

import com.newlight77.bhavio.right.model.Right;
import com.newlight77.bhavio.right.model.TemporaryRight;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString
@EqualsAndHashCode
@Table(name = "RIGHT")
public class RightJpaEntity implements Serializable{

  private static final long serialVersionUID = 2196530990380331933L;

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  private String uid;
  private String primary;
  private String secondary;
  private Set<Right> rights;
  private Set<TemporaryRight> tempRights;
  private Instant modificationDate;

}
