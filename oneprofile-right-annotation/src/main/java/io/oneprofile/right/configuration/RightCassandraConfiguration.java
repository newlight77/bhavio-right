package io.oneprofile.right.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(basePackages= "io.oneprofile.right.repository.cassandra")
@EntityScan(basePackages = "io.oneprofile.right.entity.cassandra")
public class RightCassandraConfiguration {
  RightCassandraConfiguration() {
  }

}
