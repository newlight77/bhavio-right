package com.newlight77.bhavio.right.annotation;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.Nullable;

import java.lang.annotation.Annotation;
import java.lang.annotation.IncompleteAnnotationException;
import java.util.Map;

public class RightConfigurationRegistrar implements ImportBeanDefinitionRegistrar {

  protected Class<? extends Annotation> annotation() {
    return EnableRightAspect.class;
  }

  @Override
  public void registerBeanDefinitions(AnnotationMetadata importingMetadata,
                                      BeanDefinitionRegistry registry) {

    AnnotationAttributes attributes = attributesFor(importingMetadata, annotation());
    if (attributes != null) {
      DB db = (DB) attributes.get("db");
      if (db == null) {
        throw new IncompleteAnnotationException(EnableRightAspect.class, "DB missing");
      }

      db.binders.forEach(binder -> {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder
                .rootBeanDefinition(binder.getServiceClazz())
                .addDependsOn(binder.getRepositoryBeanName());
            registry.registerBeanDefinition(binder.getServiceBeanName(), builder.getBeanDefinition());
          }
      );
    }
  }

  private static AnnotationAttributes attributesFor(AnnotatedTypeMetadata metadata, Class<?> annotationClass) {
    return AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(annotationClass.getName(), false));
  }

  private static AnnotationAttributes fromMap(@Nullable Map<String, Object> map) {
    if (map == null) {
      return null;
    }
    if (map instanceof AnnotationAttributes) {
      return (AnnotationAttributes) map;
    }
    return new AnnotationAttributes(map);
  }
}
