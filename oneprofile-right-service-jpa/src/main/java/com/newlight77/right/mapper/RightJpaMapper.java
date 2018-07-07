package com.newlight77.right.mapper;

import com.newlight77.right.entity.jpa.RightJpaEntity;
import com.newlight77.right.model.RightDto;

public class RightJpaMapper {

  public static RightDto to(RightJpaEntity entity) {
    return RightDto.builder()
            .primary(entity.getPrimary())
            .secondary(entity.getSecondary())
            .rights(entity.getRights())
            .tempRights(entity.getTempRights())
            .modificationDate(entity.getModificationDate())
            .build();
  }

  public static RightJpaEntity from(RightDto dto) {
    return RightJpaEntity.builder()
            .primary(dto.getPrimary())
            .secondary(dto.getSecondary())
            .rights(dto.getRights())
            .tempRights(dto.getTempRights())
            .modificationDate(dto.getModificationDate())
        .build();
  }
}
