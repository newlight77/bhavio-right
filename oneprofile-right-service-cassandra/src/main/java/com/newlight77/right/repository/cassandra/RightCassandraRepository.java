package com.newlight77.right.repository.cassandra;

import com.newlight77.right.entity.cassandra.RightCassandraEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface RightCassandraRepository extends CassandraRepository<RightCassandraEntity, String> {

  Iterable<RightCassandraEntity> findByPrimaryAndSecondary(String primary, String secondary);
}
