package io.oneprofile.right.repository.cassandra;

import io.oneprofile.right.entity.cassandra.RightCassandraEntity;
import io.oneprofile.right.entity.cassandra.RightCassandraEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.Collection;

public interface RightCassandraRepository extends CassandraRepository<RightCassandraEntity, String> {

  Collection<RightCassandraEntity> findByPrimaryAndSecondary(String primary, String secondary);
}
