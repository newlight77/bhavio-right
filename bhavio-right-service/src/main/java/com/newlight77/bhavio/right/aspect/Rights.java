package com.newlight77.bhavio.right.aspect;

import com.newlight77.bhavio.right.model.Right;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Rights {
  Right[] rights();
}
