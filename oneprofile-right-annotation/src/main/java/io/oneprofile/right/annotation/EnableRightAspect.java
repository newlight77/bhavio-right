package io.oneprofile.right.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({RightConfigurationRegistrar.class})
public @interface EnableRightAspect {
  DB db() default DB.MONGO;
}
