package com.newlight77.bhavio.right.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages= "com.bhavio.right.repository.mongo")
@EntityScan(basePackages = "com.bhavio.right.entity.mongo")
public class RightMongoConfiguration {
  RightMongoConfiguration() {
  }

}
