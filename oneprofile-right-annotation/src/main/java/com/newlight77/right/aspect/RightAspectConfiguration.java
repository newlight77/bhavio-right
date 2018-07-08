package com.newlight77.right.aspect;

import com.newlight77.right.service.HasRightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class RightAspectConfiguration {
  RightAspectConfiguration() {
  }

  @Autowired
  public HasRightService hasRightService;

  @Bean
  public RightAspect authorizationAspect() {
    return new RightAspect(hasRightService);
  }

}
