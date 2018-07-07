package com.newlight77.right.mapper;

import com.newlight77.right.entity.mongo.RightMongoEntity;
import com.newlight77.right.model.RightDto;

public class RightMongoMapper {

  public static RightDto to(RightMongoEntity entity) {
    return RightDto.builder()
            .primary(entity.getPrimary())
            .secondary(entity.getSecondary())
            .rights(entity.getRights())
            .tempRights(entity.getTempRights())
            .modificationDate(entity.getModificationDate())
            .build();
  }

  public static RightMongoEntity from(RightDto dto) {
    return RightMongoEntity.builder()
            .primary(dto.getPrimary())
            .secondary(dto.getSecondary())
            .rights(dto.getRights())
            .tempRights(dto.getTempRights())
            .modificationDate(dto.getModificationDate())
        .build();
  }
}
