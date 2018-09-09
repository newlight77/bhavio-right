package com.newlight77.right.service.impl;


import com.newlight77.right.entity.cassandra.RightCassandraEntity;
import com.newlight77.right.model.Right;
import com.newlight77.right.repository.cassandra.RightCassandraRepository;
import com.newlight77.right.service.HasRightService;
import com.newlight77.right.service.RightFilter;

import java.util.HashSet;
import java.util.Set;

public class HasRightCassandraService implements HasRightService {

  private RightCassandraRepository rightRepository;

  public HasRightCassandraService(RightCassandraRepository authorizationRepository) {
    this.rightRepository = authorizationRepository;
  }

  public boolean hasRight(RightFilter filter) {
    Set<Right> rights = new HashSet<>();
    Iterable<RightCassandraEntity> entities = rightRepository.findByPrimaryAndSecondary(filter.getPrimary(), filter.getSecondary());
    entities.forEach(entity -> {
      rights.addAll(entity.getRights());
    });

    for ( Right right: filter.getRights() ) {
      if (rights.contains(right)) {
        // if database contains at least one right allowed by method
        return true;
      }
    }

    return false;
  }
}
