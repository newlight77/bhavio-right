package com.newlight77.right.repository.neo4j;

import com.newlight77.right.entity.neo4j.RightNeo4jEntity;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface RightNeo4jRepository extends Neo4jRepository<RightNeo4jEntity, String> {

  Iterable<RightNeo4jEntity> findByPrimaryAndSecondary(String primary, String secondary);

  @Query("MATCH (u:Right) WHERE u.primary = {primary} AND u.secondary = {secondary} RETURN u LIMIT {limit}")
  Collection<RightNeo4jEntity> rightsByPrimaryAndSecondary(@Param("primary") String primary, @Param("secondary") String secondary, @Param("limit") int limit);

}
