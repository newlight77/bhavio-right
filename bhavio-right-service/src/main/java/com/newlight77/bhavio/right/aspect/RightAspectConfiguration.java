package com.newlight77.bhavio.right.aspect;

import com.newlight77.bhavio.right.service.RightService;
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
  public RightService rightService;

  @Bean
  public RightAspect authorizationAspect() {
    return new RightAspect(rightService);
  }

}
