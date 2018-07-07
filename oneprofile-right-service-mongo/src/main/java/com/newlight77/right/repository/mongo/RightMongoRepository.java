package com.newlight77.right.repository.mongo;

import com.newlight77.right.entity.mongo.RightMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface RightMongoRepository extends MongoRepository<RightMongoEntity, String> {

  Collection<RightMongoEntity> findByPrimaryAndSecondary(String primary, String secondary);
}
