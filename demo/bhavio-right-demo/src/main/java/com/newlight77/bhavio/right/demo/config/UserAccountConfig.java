package com.newlight77.bhavio.right.demo.config;

import com.newlight77.bhavio.right.demo.repository.UserRepository;
import com.newlight77.bhavio.right.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@Configuration
@EnableNeo4jRepositories(basePackages = { "com.newlight77.bhavio.right.demo.repository" })
@EntityScan(basePackages = "com.newlight77.bhavio.right.demo.entity")
public class UserAccountConfig {

  @Autowired
  private UserRepository userRepository;

  @Bean
  public UserService userService() {
    return new UserService(userRepository);
  }
}
