package com.newlight77.right.aspect;

import com.newlight77.right.service.HasRightService;
import com.newlight77.right.service.RightFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(proxyTargetClass = false)
@ComponentScan(value={"com.newlight77.right.aspect"})
public class HasRightTestConfig {

    @Bean
    private HasRightService hasRightService() {
        return new HasRightService() {
            @Override
            public boolean hasRight(RightFilter filter) {
                return false;
            }
        };
    }

    @Bean
    public RightAspect rightAspect() throws Exception {
        return new RightAspect(hasRightService());
    }

    @Bean
    public RightAspectUsage rightAspectUsage() throws Exception {
        return new RightAspectUsage();
    }

}
