package com.newlight77.right.demo.config;

import com.newlight77.right.annotation.DB;
import com.newlight77.right.annotation.EnableRightAspect;
import org.springframework.context.annotation.Configuration;

@EnableRightAspect(db = DB.NEO4J)
@Configuration
public class RightDemoConfig {

}
