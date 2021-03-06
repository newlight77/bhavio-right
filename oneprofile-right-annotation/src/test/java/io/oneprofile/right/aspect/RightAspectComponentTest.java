package io.oneprofile.right.aspect;

import com.google.common.collect.Sets;
import io.oneprofile.right.aspect.stub.RightEntity;
import io.oneprofile.right.aspect.stub.RightRepository;
import io.oneprofile.right.aspect.stub.RightTestConfig;
import io.oneprofile.right.exception.NoRightException;
import io.oneprofile.right.model.Right;
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

    @Test(expected = NoRightException.class)
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
        thrown.expect(NoRightException.class);
        thrown.expectMessage(
                "You don't have right access : filter = RightFilter(primary=primaryId, secondary=secondaryId, rights=[ADMIN_READ])");

    }
}

