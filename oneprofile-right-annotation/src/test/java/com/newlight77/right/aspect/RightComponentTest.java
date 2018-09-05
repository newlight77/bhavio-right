package com.newlight77.right.aspect;

import com.google.common.collect.Sets;
import com.newlight77.exception.ForbiddenException;
import com.newlight77.right.aspect.configuration.RightEntity;
import com.newlight77.right.aspect.configuration.RightRepository;
import com.newlight77.right.aspect.configuration.RightTestConfig;
import com.newlight77.right.model.Right;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {
                RightTestConfig.class,
                RightAspectConfiguration.class
        }
)
public class RightComponentTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private RightRepository rightRepositoryMock;

    @Autowired
    private RightAspect rightAspect;

    @Test
    public void shouldHasRight_whenMethodAllowReadAndDataAllowedRead() throws Throwable {
        rightAspect.annotatedWithRight();
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

class TestingService {
    @Rights(rights = {Right.ADMIN_READ})
    public String adminRead(String primary, String secondary) {
        return "allowedRead";
    }

    @Rights(rights = {Right.ADMIN_WRITE})
    public String adminWrite(String primary, String secondary) {
        return "allowedWrite";
    }


    @Rights(rights = {Right.ADMIN_READ, Right.ADMIN_WRITE})
    public String adminReadWrite(String primary, String secondary) {
        return "allowedReadWrite";
    }

    @Rights(rights = {Right.ADMIN_READ, Right.ADMIN_DELETE})
    public String adminReadDelete(String primary, String secondary) {
        return "allowedReadDelete";
    }
}

