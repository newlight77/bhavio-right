package io.oneprofile.right.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages= "io.oneprofile.right.repository.mongo")
@EntityScan(basePackages = "io.oneprofile.right.entity.mongo")
public class RightMongoConfiguration {
  RightMongoConfiguration() {
  }

}
