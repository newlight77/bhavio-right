package io.oneprofile.right.repository.mongo;

import io.oneprofile.right.entity.mongo.RightMongoEntity;
import io.oneprofile.right.entity.mongo.RightMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface RightMongoRepository extends MongoRepository<RightMongoEntity, String> {

  Collection<RightMongoEntity> findByPrimaryAndSecondary(String primary, String secondary);
}
