package io.oneprofile.right.repository.neo4j;

import io.oneprofile.right.entity.neo4j.RightNeo4jEntity;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface RightNeo4jRepository extends Neo4jRepository<RightNeo4jEntity, String> {

  @Query("MATCH (r:Right) WHERE r.primary = {primary} AND r.secondary = {secondary} RETURN r LIMIT {limit}")
  Collection<RightNeo4jEntity> findByPrimaryAndSecondary(@Param("primary") String primary, @Param("secondary") String secondary, @Param("limit") int limit);

  @Query("MATCH (r:Right) WHERE r.primary = {primary} AND r.secondary = {secondary} DELETE r")
  Collection<RightNeo4jEntity> deleteByPrimaryAndSecondary(@Param("primary") String primary, @Param("secondary") String secondary);

}
