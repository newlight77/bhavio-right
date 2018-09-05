package com.newlight77.right.aspect.configuration;

import com.newlight77.right.service.HasRightService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
@EnableAspectJAutoProxy(proxyTargetClass = false)
@ComponentScan(value={"com.newlight77.right.aspect", "com.newlight77.right.annotation"})
public class RightTestConfig {

    @MockBean
    private RightRepository rightRepositoryMock;

    @Bean
    public HasRightService rightService() {
        return new RightServiceStub(rightRepositoryMock);
    }

}
