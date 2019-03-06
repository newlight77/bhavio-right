package io.oneprofile.right.demo.config;

import io.oneprofile.right.annotation.DB;
import io.oneprofile.right.annotation.EnableRightAspect;
import io.oneprofile.right.demo.service.RightCrudService;
import io.oneprofile.right.repository.neo4j.RightNeo4jRepository;
import io.oneprofile.right.service.HasRightService;
import io.oneprofile.right.service.impl.HasRightNeo4JService;
import io.oneprofile.right.annotation.DB;
import io.oneprofile.right.annotation.EnableRightAspect;
import io.oneprofile.right.demo.service.RightCrudService;
import io.oneprofile.right.repository.neo4j.RightNeo4jRepository;
import io.oneprofile.right.service.HasRightService;
import io.oneprofile.right.service.impl.HasRightNeo4JService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRightAspect(db = DB.NEO4J)
@Configuration
public class RightDemoConfig {

    @Autowired
    RightNeo4jRepository rightNeo4jRepository;

    @Bean
    public HasRightService rightService() {
        return new HasRightNeo4JService(rightNeo4jRepository);
    }

    @Bean
    public RightCrudService rightCrudService() {
        return new RightCrudService(rightNeo4jRepository);
    }

}
