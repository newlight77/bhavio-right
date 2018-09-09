package com.newlight77.right.aspect;

import com.newlight77.right.service.HasRightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=false)
@ComponentScan(value={"com.newlight77.right.aspect"})
public class RightAspectConfiguration {

  @Autowired
  private HasRightService hasRightService;

  @Bean
  public RightAspect rightAspect() throws Exception {
    return new RightAspect(hasRightService);
  }

}