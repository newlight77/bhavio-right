package com.newlight77.right.service.impl;


import com.newlight77.right.entity.jpa.RightJpaEntity;
import com.newlight77.right.model.Right;
import com.newlight77.right.repository.jpa.RightJpaRepository;
import com.newlight77.right.service.RightFilter;
import com.newlight77.right.service.RightService;

import java.util.HashSet;
import java.util.Set;

public class RightJpaService implements RightService {

  private RightJpaRepository rightRepository;

  public RightJpaService(RightJpaRepository authorizationRepository) {
    this.rightRepository = authorizationRepository;
  }

  public boolean hasRight(RightFilter filter) {
    Set<Right> rights = new HashSet<>();
    Iterable<RightJpaEntity> entities = rightRepository.findByPrimaryAndSecondary(filter.getPrimary(), filter.getSecondary());
    entities.forEach(entity -> {
      rights.addAll(entity.getRights());
    });
    rights.removeAll(filter.getRights());
    return !rights.isEmpty();
  }

}
