package com.newlight77.bhavio.right.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.bhavio")
public class DemoApplication {

  private final Logger LOGGER = LoggerFactory.getLogger(DemoApplication.class);

  public static void main(String[] args) throws Exception {
    SpringApplication.run(DemoApplication.class, args);
  }
}
