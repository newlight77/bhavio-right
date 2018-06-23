package com.newlight77.right.service.impl;


import com.newlight77.right.entity.mongo.RightMongoEntity;
import com.newlight77.right.model.Right;
import com.newlight77.right.model.TemporaryRight;
import com.newlight77.right.repository.cassandra.RightCassandraRepository;
import com.newlight77.right.service.RightFilter;
import com.newlight77.right.service.RightService;
import com.newlight77.right.repository.cassandra.RightCassandraRepository;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class RightCassandraService implements RightService {

  private RightCassandraRepository rightRepository;

  public RightCassandraService(RightCassandraRepository authorizationRepository) {
    this.rightRepository = authorizationRepository;
  }

  public void addRight(String primary, String secondary, Set<Right> rights, Set<TemporaryRight> tempRights) {
    RightMongoEntity entity = RightMongoEntity.builder()
        .modificationDate(Instant.now())
        .primary(primary)
        .secondary(secondary)
        .rights(rights)
        .tempRights(tempRights)
        .build();
    rightRepository.save(entity);
  }

  public boolean hasRight(RightFilter filter) {
    Set<Right> rights = new HashSet<>();
    Iterable<RightMongoEntity> entities = rightRepository.findByPrimaryAndSecondary(filter.getPrimary(), filter.getSecondary());
    entities.forEach(entity -> {
      rights.addAll(entity.getRights());
    });
    rights.removeAll(filter.getRights());
    return !rights.isEmpty();
  }
}
