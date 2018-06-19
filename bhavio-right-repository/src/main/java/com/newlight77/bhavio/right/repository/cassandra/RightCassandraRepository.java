package com.newlight77.bhavio.right.repository.cassandra;

import com.newlight77.bhavio.right.entity.mongo.RightMongoEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface RightCassandraRepository extends CassandraRepository<RightMongoEntity, String> {

  Iterable<RightMongoEntity> findByPrimaryAndSecondary(String primary, String secondary);
}
