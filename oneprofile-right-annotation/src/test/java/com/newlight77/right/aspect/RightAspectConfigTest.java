package com.newlight77.right.aspect;

import com.newlight77.exception.ForbiddenException;
import com.newlight77.right.model.Right;
import com.newlight77.right.service.HasRightService;
import com.newlight77.right.service.RightFilter;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={HasRightTestConfig.class})
public class RightAspectConfigTest extends AbstractJUnit4SpringContextTests {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private RightAspectUsage rightAspectClient;

    @Autowired
    private HasRightService hasRightServiceMock;

    @Test
    public void shouldHasRight_whenMethodAllowReadAndDataAllowedRead() throws Throwable {

        // Given
        String primary = "primaryId";
        String secondary = "secondaryId";

        RightFilter filter = RightFilter.builder()
                .primary(primary)
                .secondary(secondary)
                .rights(Arrays.asList(Right.ADMIN_READ))
                .build();
        when(hasRightServiceMock
                .hasRight(filter))
                .thenReturn(true); // authorized

        // When
        String result = rightAspectClient.adminRead(primary, secondary);

        // Then
        Assertions.assertThat(result).isEqualTo("allowedRead");
    }

    @Test(expected = ForbiddenException.class)
    public void shouldNotHasRight_whenMethodAllowWriteAndDataNotAllowedWrite() throws Throwable {
        // Given
        String primary = "primaryId";
        String secondary = "secondaryId";

        RightFilter filter = RightFilter.builder()
                .primary(primary)
                .secondary(secondary)
                .rights(Arrays.asList(Right.ADMIN_WRITE))
                .build();
        when(hasRightServiceMock
                .hasRight(filter))
                .thenReturn(false); // authorized

        // When
        String result = rightAspectClient.adminWrite(primary, secondary);

        // Then
        thrown.expect(ForbiddenException.class);
        thrown.expectMessage(
                "You don't have access : filter = RightFilter(primary=primaryId, secondary=secondaryId, rights=[ADMIN_WRITE])");

    }
}


