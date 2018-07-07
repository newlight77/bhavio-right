package com.newlight77.right.mapper;

import com.newlight77.right.entity.cassandra.RightCassandraEntity;
import com.newlight77.right.model.RightDto;

public class RightCassandraMapper {

  public static RightDto to(RightCassandraEntity entity) {
    return RightDto.builder()
            .primary(entity.getPrimary())
            .secondary(entity.getSecondary())
            .rights(entity.getRights())
            .tempRights(entity.getTempRights())
            .modificationDate(entity.getModificationDate())
            .build();
  }

  public static RightCassandraEntity from(RightDto dto) {
    return RightCassandraEntity.builder()
            .primary(dto.getPrimary())
            .secondary(dto.getSecondary())
            .rights(dto.getRights())
            .tempRights(dto.getTempRights())
            .modificationDate(dto.getModificationDate())
        .build();
  }
}
