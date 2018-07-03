package com.newlight77.right.repository.mongo;

import com.newlight77.right.entity.mongo.RightMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RightMongoRepository extends MongoRepository<RightMongoEntity, String> {

  Iterable<RightMongoEntity> findByPrimaryAndSecondary(String primary, String secondary);
}
