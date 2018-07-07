package com.newlight77.right.service.impl;


import com.newlight77.right.entity.neo4j.RightNeo4jEntity;
import com.newlight77.right.mapper.RightNeo4jMapper;
import com.newlight77.right.model.Right;
import com.newlight77.right.model.RightDto;
import com.newlight77.right.repository.neo4j.RightNeo4jRepository;
import com.newlight77.right.service.RightFilter;
import com.newlight77.right.service.RightService;

import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RightNeo4JService implements RightService {

  private RightNeo4jRepository rightRepository;

  public RightNeo4JService(RightNeo4jRepository authorizationRepository) {
    this.rightRepository = authorizationRepository;
  }

  public RightDto addRight(RightDto rightDto) {
    RightNeo4jEntity entity = RightNeo4jEntity.builder()
        .modificationDate(Instant.now())
        .primary(rightDto.getPrimary())
        .secondary(rightDto.getSecondary())
        .rights(rightDto.getRights())
        .tempRights(rightDto.getTempRights())
        .modificationDate(Instant.now())
        .build();
    return RightNeo4jMapper.to(rightRepository.save(entity));
  }


  @Override
  public Collection<RightDto> findByPrimaryAndSecondary(String primary, String secondary) {
    Collection<RightNeo4jEntity> entities =
            rightRepository.findByPrimaryAndSecondary(primary, secondary, 100);
    return entities.stream().map(e -> RightNeo4jMapper.to(e)).collect(Collectors.toSet());
  }

  @Override
  public void deleteByPrimaryAndSecondary(String primary, String secondary) {
    rightRepository.deleteById(primary);
  }

  public boolean hasRight(RightFilter filter) {
    Set<Right> rights = new HashSet<>();
    Collection<RightNeo4jEntity> entities =
            rightRepository.findByPrimaryAndSecondary(filter.getPrimary(), filter.getSecondary(), 100);
    entities.forEach(entity -> {
      rights.addAll(entity.getRights());
    });
    rights.removeAll(filter.getRights());
    return !rights.isEmpty();
  }
}
