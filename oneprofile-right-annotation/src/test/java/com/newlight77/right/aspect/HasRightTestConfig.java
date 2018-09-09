package com.newlight77.right.aspect;

import com.newlight77.right.service.HasRightService;
import com.newlight77.right.service.RightFilter;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(proxyTargetClass = false)
@ComponentScan(value={"com.newlight77.right.aspect"})
public class HasRightTestConfig {

    @MockBean
    private HasRightService hasRightServiceMock;

    @Bean
    public RightAspect rightAspect() throws Exception {
        return new RightAspect(hasRightServiceMock);
    }

    @Bean
    public RightAspectUsage rightAspectUsage() throws Exception {
        return new RightAspectUsage();
    }

}
