package com.newlight77.right.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.newlight77")
public class RightDemoApplication {

  private final Logger LOGGER = LoggerFactory.getLogger(RightDemoApplication.class);

  public static void main(String[] args) throws Exception {
    SpringApplication.run(RightDemoApplication.class, args);
  }
}
