package io.oneprofile.right.demo.config;

import io.oneprofile.right.demo.RightsInjectionRunner;
import io.oneprofile.right.repository.neo4j.RightNeo4jRepository;
import io.oneprofile.right.repository.neo4j.RightNeo4jRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@Configuration
@EnableNeo4jRepositories(basePackages = { "io.oneprofile.right.neo4j.repository" })
@EntityScan(basePackages = "io.oneprofile.right.neo4j.entity")
public class DataInjectionConfig {

  @Autowired
  private RightNeo4jRepository rightRepository;

  @Bean
  CommandLineRunner rightsInjectionRunner() {
    return new RightsInjectionRunner(rightRepository);
  }
}
