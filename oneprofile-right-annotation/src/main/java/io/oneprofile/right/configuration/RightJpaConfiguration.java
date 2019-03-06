package io.oneprofile.right.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages= "io.oneprofile.right.repository.jpa")
@EntityScan(basePackages = "io.oneprofile.right.entity.jpa")
public class RightJpaConfiguration {
  RightJpaConfiguration() {
  }

}
