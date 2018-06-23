package com.newlight77.right.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages= "com.newlight77.right.repository.jpa")
@EntityScan(basePackages = "com.newlight77.right.entity.jpa")
public class RightJpaConfiguration {
  RightJpaConfiguration() {
  }

}
