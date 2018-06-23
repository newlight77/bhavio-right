package com.newlight77.right.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(basePackages= "com.newlight77.right.repository.cassandra")
@EntityScan(basePackages = "com.newlight77.right.entity.cassandra")
public class RightCassandraConfiguration {
  RightCassandraConfiguration() {
  }

}
