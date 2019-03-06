package io.oneprofile.right.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages="io.oneprofile")
@ComponentScan(basePackages = "io.oneprofile")
public class RightDemoApplication {

  private final Logger LOGGER = LoggerFactory.getLogger(RightDemoApplication.class);

  public static void main(String[] args) throws Exception {
    SpringApplication.run(RightDemoApplication.class, args);
  }
}
