package com.newlight77.right.aspect;

import com.google.common.collect.Sets;
import com.newlight77.exception.ForbiddenException;
import com.newlight77.right.aspect.stub.RightEntity;
import com.newlight77.right.aspect.stub.RightRepository;
import com.newlight77.right.aspect.stub.RightServiceStub;
import com.newlight77.right.aspect.stub.TestingService;
import com.newlight77.right.model.Right;
import com.newlight77.right.service.HasRightService;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
//@SpringBootTest(
//        classes = {
////                RightTestConfig.class,
//                RightAspectConfiguration.class
//        }
//)
@ContextConfiguration(classes={RightAspectConfiguration.class})
public class RightComponentTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @MockBean
    private RightRepository rightRepositoryMock;

    @Bean
    public HasRightService hasRightService() {
        return new RightServiceStub(rightRepositoryMock);
    }

//    @Autowired
//    private RightRepository rightRepositoryMock;

    @Test
    public void shouldHasRight_whenMethodAllowReadAndDataAllowedRead() throws Throwable {
        String primary = "primaryId";
        String secondary = "secondaryId";
        RightEntity rightEntity = new RightEntity("primaryId", "secondaryId", Sets.newHashSet(Right.ADMIN_READ));

        when(rightRepositoryMock
                .findByPrimaryAndSecondary(primary, secondary, 100))
                .thenReturn(Arrays.asList(rightEntity)); // authorized

        String result = new TestingService().adminRead(primary, secondary);

        Assertions.assertThat(result).isEqualTo("allowedRead");
    }

    @Test
    public void shouldHasRight_whenMethodAllowWriteAndDataNotAllowedWrite() throws Throwable {
        // Given
        String primary = "primaryId";
        String secondary = "secondaryId";
        RightEntity rightEntity = new RightEntity("primaryId", "secondaryId", Sets.newHashSet(Right.ADMIN_READ));

        when(rightRepositoryMock
                .findByPrimaryAndSecondary(primary, secondary, 100))
                .thenReturn(Arrays.asList(rightEntity)); // authorized

        // When
        String result = new TestingService().adminWrite(primary, secondary);

        // Then
        thrown.expect(ForbiddenException.class);
        thrown.expectMessage(
                "You don't have access : filter = RightFilter(primary=primaryId, secondary=secondaryId, rights=[ADMIN_READ])");

    }
}

