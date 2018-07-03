package com.newlight77.right.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages= "com.newlight77.right.repository.mongo")
@EntityScan(basePackages = "com.newlight77.right.entity.mongo")
public class RightMongoConfiguration {
  RightMongoConfiguration() {
  }

}
