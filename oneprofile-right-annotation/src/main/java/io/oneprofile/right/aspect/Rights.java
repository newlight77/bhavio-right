package io.oneprofile.right.aspect;

import io.oneprofile.right.model.Right;
import io.oneprofile.right.model.Right;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Rights {
  Right[] rights();
}
