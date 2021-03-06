package io.oneprofile.right.aspect;

import io.oneprofile.right.exception.NoRightException;
import io.oneprofile.right.model.Right;
import io.oneprofile.right.service.HasRightService;
import io.oneprofile.right.service.RightFilter;
import io.oneprofile.right.exception.NoRightException;
import io.oneprofile.right.service.HasRightService;
import io.oneprofile.right.service.RightFilter;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Aspect
public class RightAspect {
  private static final Logger LOGGER = LoggerFactory.getLogger(RightAspect.class);

  private static final List<String> PRIMARY_PARAM = Arrays.asList("primary", "username");
  private static final List<String> SECONDARY_PARAM = Arrays.asList("secondary");

  private final HasRightService hasRightService;

  public RightAspect(HasRightService hasRightService) {
    this.hasRightService = hasRightService;
  }

  @Pointcut("within(@io.oneprofile.right.aspect.Rights *) || @annotation(io.oneprofile.right.aspect.Rights)")
  public void annotatedWithRight() {}

  @Before(value = "annotatedWithRight()")
  public void before(JoinPoint call) throws Throwable {
    Signature signature = call.getStaticPart().getSignature();
    String clazz = call.getTarget().getClass().getSimpleName();
    if (signature instanceof MethodSignature) {
      MethodSignature ms = (MethodSignature) signature;
      Method method = ms.getMethod();
      String[] argNames = ms.getParameterNames();
      Object[] argValues = call.getArgs();
      checkRights(buildFilter(clazz, method, argNames, argValues));
    }
  }

  @SuppressWarnings("unchecked")
  private RightFilter buildFilter(String clazz,
                                  Method method,
                                  String[] argNames,
                                  Object[] argValues) {
    String primary = "";
    String secondary = clazz;

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

  private void checkRights(RightFilter rightFilter) {
    LOGGER.debug("Verification of right : {}", rightFilter);
    if (!hasRightService.hasRight(rightFilter)) {
      throw new NoRightException("You don't have right access : filter = " + rightFilter);
    }
  }
}