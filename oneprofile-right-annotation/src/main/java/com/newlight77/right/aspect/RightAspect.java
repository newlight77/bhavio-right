package com.newlight77.right.aspect;

import com.newlight77.exception.ForbiddenException;
import com.newlight77.right.model.Right;
import com.newlight77.right.service.RightFilter;
import com.newlight77.right.service.RightService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Aspect
@Component
public class RightAspect {
  private static final Logger LOGGER = LoggerFactory.getLogger(RightAspect.class);

  private static final List<String> PRIMARY_PARAM = Arrays.asList("primary", "username");
  private static final List<String> SECONDARY_PARAM = Arrays.asList("secondary", "username");

  private final RightService rightService;

  public RightAspect(RightService rightService) {
    this.rightService = rightService;
  }

  @Pointcut("within(@com.newlight77.right.aspect.Rights *) || @annotation(com.newlight77.right.aspect.Rights)")
  public void annotatedWithRight() {}

  @Before(value = "annotatedWithRight()")
  public void before(JoinPoint call) throws Throwable {
    Signature signature = call.getStaticPart().getSignature();
    if (signature instanceof MethodSignature) {
      MethodSignature ms = (MethodSignature) signature;
      Method method = ms.getMethod();
      String[] argNames = ms.getParameterNames();
      Object[] argValues = call.getArgs();
      checkRights(buildFilter(method, argNames, argValues));
    }
  }

  @SuppressWarnings("unchecked")
  private RightFilter buildFilter(Method method,
                                  String[] argNames,
                                  Object[] argValues) {
    String primary = "";
    String secondary = "";

     if (null != argNames
        && null != argValues
        && argNames.length > 0
        && argValues.length == argNames.length) {
      for (int i = 0; i < argNames.length; i++) {
        if (StringUtils.isNotBlank(argNames[i]) && PRIMARY_PARAM.contains(argNames[i])) {
          primary = (String) argValues[i];
        }

        if (StringUtils.isNotBlank(argNames[i]) && SECONDARY_PARAM.contains(argNames[i])) {
          secondary = (String) argValues[i];
        }
      }
    }

    Rights myAnnotation = method.getAnnotation(Rights.class);
    Collection<Right> rights = Arrays.asList(myAnnotation.rights());
    return RightFilter.builder().primary(primary).secondary(secondary).rights(rights).build();
  }

  private void checkRights(RightFilter accessRightFilter) {
    LOGGER.debug("Verification of right : {}", accessRightFilter);
    if (!rightService.hasRight(accessRightFilter)) {
      throw new ForbiddenException("You don't have access : filter = " + accessRightFilter);
    }
  }

}
