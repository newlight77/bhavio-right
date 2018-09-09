package com.newlight77.right.service.impl;


import com.newlight77.right.entity.neo4j.RightNeo4jEntity;
import com.newlight77.right.model.Right;
import com.newlight77.right.repository.neo4j.RightNeo4jRepository;
import com.newlight77.right.service.HasRightService;
import com.newlight77.right.service.RightFilter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class HasRightNeo4JService implements HasRightService {

  private RightNeo4jRepository rightRepository;

  public HasRightNeo4JService(RightNeo4jRepository authorizationRepository) {
    this.rightRepository = authorizationRepository;
  }

  public boolean hasRight(RightFilter filter) {
    Set<Right> rights = new HashSet<>();
    Collection<RightNeo4jEntity> entities =
            rightRepository.findByPrimaryAndSecondary(filter.getPrimary(), filter.getSecondary(), 100);
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
