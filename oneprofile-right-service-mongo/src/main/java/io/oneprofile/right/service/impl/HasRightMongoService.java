package io.oneprofile.right.service.impl;


import io.oneprofile.right.entity.mongo.RightMongoEntity;
import io.oneprofile.right.model.Right;
import io.oneprofile.right.repository.mongo.RightMongoRepository;
import io.oneprofile.right.service.HasRightService;
import io.oneprofile.right.service.RightFilter;

import java.util.HashSet;
import java.util.Set;

public class HasRightMongoService implements HasRightService {

  private RightMongoRepository rightRepository;

  public HasRightMongoService(RightMongoRepository authorizationRepository) {
    this.rightRepository = authorizationRepository;
  }

  public boolean hasRight(RightFilter filter) {
    Set<Right> rights = new HashSet<>();
    Iterable<RightMongoEntity> entities = rightRepository.findByPrimaryAndSecondary(filter.getPrimary(), filter.getSecondary());
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
