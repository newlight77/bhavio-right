package io.oneprofile.right.demo.mapper;

import io.oneprofile.right.demo.model.RightDto;
import io.oneprofile.right.entity.neo4j.RightNeo4jEntity;

import java.time.Instant;

public class RightMapper {

  public static RightDto to(RightNeo4jEntity entity) {
    return RightDto.builder()
            .primary(entity.getPrimary())
            .secondary(entity.getSecondary())
            .rights(entity.getRights())
            .tempRights(entity.getTempRights())
            .modificationDate(entity.getModificationDate())
            .build();
  }

  public static RightNeo4jEntity from(RightDto dto) {
    return RightNeo4jEntity.builder()
            .primary(dto.getPrimary())
            .secondary(dto.getSecondary())
            .rights(dto.getRights())
            .tempRights(dto.getTempRights())
            .modificationDate(Instant.now())
        .build();
  }
}
