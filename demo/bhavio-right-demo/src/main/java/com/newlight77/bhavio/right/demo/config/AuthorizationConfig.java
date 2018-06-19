package com.newlight77.bhavio.right.demo.config;

import com.newlight77.bhavio.right.annotation.DB;
import com.newlight77.bhavio.right.annotation.EnableRightAspect;
import org.springframework.context.annotation.Configuration;

@EnableRightAspect(db = DB.MONGO)
@Configuration
public class AuthorizationConfig {
}
