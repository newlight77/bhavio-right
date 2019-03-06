package io.oneprofile.right.aspect;

import io.oneprofile.right.service.HasRightService;
import io.oneprofile.right.service.HasRightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=false)
@ComponentScan(value={"io.oneprofile.right.aspect"})
public class RightAspectConfiguration {

  @Autowired
  private HasRightService hasRightService;

  @Bean
  public RightAspect rightAspect() throws Exception {
    return new RightAspect(hasRightService);
  }

}