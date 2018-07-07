package com.newlight77.right.service.impl;


import com.newlight77.right.entity.mongo.RightMongoEntity;
import com.newlight77.right.mapper.RightMongoMapper;
import com.newlight77.right.model.Right;
import com.newlight77.right.model.RightDto;
import com.newlight77.right.repository.mongo.RightMongoRepository;
import com.newlight77.right.service.RightFilter;
import com.newlight77.right.service.RightService;

import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RightMongoService implements RightService {

  private RightMongoRepository rightRepository;

  public RightMongoService(RightMongoRepository authorizationRepository) {
    this.rightRepository = authorizationRepository;
  }

  public RightDto addRight(RightDto rightDto) {
    RightMongoEntity entity = RightMongoEntity.builder()
        .modificationDate(Instant.now())
        .primary(rightDto.getPrimary())
        .secondary(rightDto.getSecondary())
        .rights(rightDto.getRights())
        .tempRights(rightDto.getTempRights())
        .modificationDate(Instant.now())
        .build();
    return RightMongoMapper.to(rightRepository.save(entity));
  }

  @Override
  public Collection<RightDto> findByPrimaryAndSecondary(String primary, String secondary) {
    Collection<RightMongoEntity> entities =
            rightRepository.findByPrimaryAndSecondary(primary, secondary);
    return entities.stream().map(e -> RightMongoMapper.to(e)).collect(Collectors.toSet());
  }

  @Override
  public void deleteByPrimaryAndSecondary(String primary, String secondary) {
    rightRepository.deleteById(primary);
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
