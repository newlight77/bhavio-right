package com.newlight77.right.repository.neo4j;

import com.newlight77.right.entity.neo4j.RightNeo4jEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface RightNeo4jRepository extends Neo4jRepository<RightNeo4jEntity, String> {

  Iterable<RightNeo4jEntity> findByPrimaryAndSecondary(String primary, String secondary);
}
