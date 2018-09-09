package com.newlight77.right.aspect;

import com.google.common.collect.Sets;
import com.newlight77.exception.ForbiddenException;
import com.newlight77.right.aspect.stub.RightEntity;
import com.newlight77.right.aspect.stub.RightRepository;
import com.newlight77.right.aspect.stub.RightTestConfig;
import com.newlight77.right.model.Right;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={RightTestConfig.class})
public class RightAspectComponentTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private RightRepository rightRepositoryMock;

    @Autowired
    private RightAspectUsage rightAspectUsage;

    @Test
    public void shouldHasRight_whenMethodAllowReadAndDataAllowedRead() throws Throwable {
        String primary = "primaryId";
        String secondary = "secondaryId";
        RightEntity rightEntity = new RightEntity("primaryId", "secondaryId", Sets.newHashSet(Right.ADMIN_READ));

        when(rightRepositoryMock
                .findByPrimaryAndSecondary(Mockito.eq(primary), Mockito.eq(secondary), Mockito.eq(100)))
                .thenReturn(Arrays.asList(rightEntity)); // authorized

        String result = rightAspectUsage.adminRead(primary, secondary);

        Assertions.assertThat(result).isEqualTo("allowedRead");
    }

    @Test(expected = ForbiddenException.class)
    public void shouldNotHasRight_whenMethodAllowWriteAndDataNotAllowedWrite() throws Throwable {
        // Given
        String primary = "primaryId";
        String secondary = "secondaryId";
        RightEntity rightEntity = new RightEntity("primaryId", "secondaryId", Sets.newHashSet(Right.ADMIN_READ));

        when(rightRepositoryMock
                .findByPrimaryAndSecondary(Mockito.eq(primary), Mockito.eq(secondary), Mockito.eq(100)))
                .thenReturn(Arrays.asList(rightEntity)); // authorized

        // When
        String result = rightAspectUsage.adminWrite(primary, secondary);

        // Then
        thrown.expect(ForbiddenException.class);
        thrown.expectMessage(
                "You don't have access : filter = RightFilter(primary=primaryId, secondary=secondaryId, rights=[ADMIN_READ])");

    }
}

