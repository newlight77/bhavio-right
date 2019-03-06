package io.oneprofile.right.demo.config;

import io.oneprofile.right.demo.repository.UserRepository;
import io.oneprofile.right.demo.service.UserService;
import io.oneprofile.right.demo.repository.UserRepository;
import io.oneprofile.right.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@Configuration
@EnableNeo4jRepositories(basePackages = { "io.oneprofile.right.demo.repository" })
@EntityScan(basePackages = "io.oneprofile.right.demo.entity")
public class UserResourceConfig {

  @Autowired
  private UserRepository userRepository;

  @Bean
  public UserService userService() {
    return new UserService(userRepository);
  }
}
