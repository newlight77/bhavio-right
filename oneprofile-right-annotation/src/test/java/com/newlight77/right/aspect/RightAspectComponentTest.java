package com.newlight77.right.aspect;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={HasRightTestConfig.class})
public class RightAspectComponentTest extends AbstractJUnit4SpringContextTests {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private RightAspectUsage rightAspectClient;

    @Test
    public void shouldHasRight_whenMethodAllowReadAndDataAllowedRead() throws Throwable {

        // Given
        String primary = "primaryId";
        String secondary = "secondaryId";

        // When
        String result = rightAspectClient.adminRead(primary, secondary);

        // Then
        Assertions.assertThat(result).isEqualTo("allowedRead");
    }

    @Test
    public void shouldNotHasRight_whenMethodAllowWriteAndDataNotAllowedWrite() throws Throwable {
        // Given
        String primary = "primaryId";
        String secondary = "secondaryId";

        // When
        String result = rightAspectClient.adminRead(primary, secondary);

        // Then
        Assertions.assertThat(result).isEqualTo("allowedRead");
    }
}


