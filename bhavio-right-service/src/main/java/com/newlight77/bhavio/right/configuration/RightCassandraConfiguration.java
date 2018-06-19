package com.newlight77.bhavio.right.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(basePackages= "com.bhavio.right.repository.cassandra")
@EntityScan(basePackages = "com.bhavio.right.entity.cassandra")
public class RightCassandraConfiguration {
  RightCassandraConfiguration() {
  }

}
