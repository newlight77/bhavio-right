package com.newlight77.right.aspect;

import com.newlight77.right.aspect.stub.RightTestConfig;
import com.newlight77.right.exception.NoRightException;
import com.newlight77.right.model.Right;
import com.newlight77.right.service.HasRightService;
import com.newlight77.right.service.RightFilter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.Import;

import java.lang.reflect.Method;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@Import(RightTestConfig.class)
public class RightAspectTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private HasRightService hasRightServiceMock;

    @Mock
    private JoinPoint joinPointMock;

    @Mock
    private JoinPoint.StaticPart staticPartMock;

    @Mock
    private MethodSignature methodSignatureMock;

    private RightAspect rightAspect;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        rightAspect = new RightAspect(hasRightServiceMock);
    }

    @Test
    @Rights(rights = {Right.ADMIN_READ})
    public void shouldHasRight_whenMethodAllow() throws Throwable {

        // given
        String[] parametersNames = {"primary", "secondary", "rights"};
        String[] parametersValues = {"primaryId", "secondaryId", "ADMIN_READ"};
        Method method = this.getClass().getMethod("shouldHasRight_whenMethodAllow");

        when(joinPointMock.getTarget()).thenReturn(this);
        when(joinPointMock.getStaticPart()).thenReturn(staticPartMock);
        when(staticPartMock.getSignature()).thenReturn(methodSignatureMock);
        when(methodSignatureMock.getParameterNames()).thenReturn(parametersNames);
        when(joinPointMock.getArgs()).thenReturn(parametersValues);
        when(methodSignatureMock.getMethod()).thenReturn(method);

        RightFilter filter = RightFilter.builder()
                .primary("primaryId")
                .secondary("secondaryId")
                .rights(Arrays.asList(Right.ADMIN_READ))
                .build();
        when(hasRightServiceMock.hasRight(eq(filter))).thenReturn(true); // authorized

        // when
        rightAspect.before(joinPointMock);

        // Then
        verify(joinPointMock).getStaticPart();
        verify(staticPartMock).getSignature();
        verify(methodSignatureMock).getParameterNames();
        verify(joinPointMock).getArgs();
        verify(methodSignatureMock).getMethod();
        verify(hasRightServiceMock).hasRight(eq(filter));
    }

    @Test
    @Rights(rights = {Right.ADMIN_WRITE})
    public void shouldThowException_whenRightsNotAllowedByMethod() throws Throwable {

        // given
        String[] parametersNames = {"primary", "secondary", "rights"};
        String[] parametersValues = {"primaryId", "secondaryId", "ADMIN_WRITE"};
        Method method = this.getClass().getMethod("shouldThowException_whenRightsNotAllowedByMethod");

        when(joinPointMock.getTarget()).thenReturn(this);
        when(joinPointMock.getStaticPart()).thenReturn(staticPartMock);
        when(staticPartMock.getSignature()).thenReturn(methodSignatureMock);
        when(methodSignatureMock.getParameterNames()).thenReturn(parametersNames);
        when(joinPointMock.getArgs()).thenReturn(parametersValues);
        when(methodSignatureMock.getMethod()).thenReturn(method);

        // not authorized, service should return missing right required by method
        RightFilter filter = RightFilter.builder()
                .primary("primaryId")
                .secondary("secondaryId")
                .rights(Arrays.asList(Right.ADMIN_WRITE))
                .build();
        when(hasRightServiceMock.hasRight(eq(filter))).thenReturn(false);

        thrown.expect(NoRightException.class);
        thrown.expectMessage(
                "You don't have right access : filter = RightFilter(primary=primaryId, secondary=secondaryId, rights=[ADMIN_WRITE])");

        // when
        rightAspect.before(joinPointMock);

        // Then
        // expect a ForbiddenException to be thrown
    }

}
