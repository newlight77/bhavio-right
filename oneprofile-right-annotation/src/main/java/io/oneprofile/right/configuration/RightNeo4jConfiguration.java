package io.oneprofile.right.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@Configuration
@EnableNeo4jRepositories(basePackages="io.oneprofile.right.repository.neo4j")
@EntityScan(basePackages = "io.oneprofile.right.entity.neo4j")
public class RightNeo4jConfiguration {
  RightNeo4jConfiguration() {
  }
}
