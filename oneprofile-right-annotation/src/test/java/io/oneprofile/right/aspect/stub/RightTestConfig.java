package io.oneprofile.right.aspect.stub;

import io.oneprofile.right.aspect.RightAspect;
import io.oneprofile.right.aspect.RightAspectUsage;
import io.oneprofile.right.service.HasRightService;
import io.oneprofile.right.aspect.RightAspect;
import io.oneprofile.right.service.HasRightService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(proxyTargetClass = false)
@ComponentScan(value={"io.oneprofile.right.aspect"})
public class RightTestConfig {

    @MockBean
    private RightRepository rightRepositoryMock;

    @Bean
    public HasRightService hasRightService() {
        return new RightServiceStub(rightRepositoryMock);
    }

    @Bean
    public RightAspect rightAspect() throws Exception {
        return new RightAspect(hasRightService());
    }

    @Bean
    public RightAspectUsage rightAspectUsage() {
        return new RightAspectUsage();
    }

}
