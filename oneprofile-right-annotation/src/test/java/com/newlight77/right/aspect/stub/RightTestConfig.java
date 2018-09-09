package com.newlight77.right.aspect.stub;

import com.newlight77.right.service.HasRightService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

//@ContextConfiguration
//@EnableAspectJAutoProxy(proxyTargetClass = false)
//@ComponentScan(value={"com.newlight77.right.aspect", "com.newlight77.right.annotation"})
public class RightTestConfig {

    @MockBean
    private RightRepository rightRepositoryMock;

    @Bean
    public HasRightService hasRightService() {
        return new RightServiceStub(rightRepositoryMock);
    }

}
