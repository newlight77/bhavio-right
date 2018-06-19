package com.newlight77.bhavio.right.annotation;

import com.newlight77.bhavio.right.service.impl.RightCassandraService;
import com.newlight77.bhavio.right.service.impl.RightJpaService;
import com.newlight77.bhavio.right.service.impl.RightMongoService;
import com.newlight77.bhavio.right.service.impl.RightNeo4JService;

import java.util.Arrays;
import java.util.List;

public enum DB {

  JPA(Arrays.asList(
      new Binder(RightJpaService.class, "rightService", "rightJpaRepository")
  )),

  CASSANDRA(Arrays.asList(
      new Binder(RightCassandraService.class, "rightService", "rightCassandraRepository")
  )),

  MONGO(Arrays.asList(
      new Binder(RightMongoService.class, "rightService", "rightMongoRepository")
  )),

  NEO4J(Arrays.asList(
      new Binder(RightNeo4JService.class, "rightService", "rightNeo4jRepository")
  ));

  List<Binder> binders;

  DB(List<Binder> list) {
    binders = list;
  }

  public List<Binder> getBinders() {
    return binders;
  }

  static class Binder {
    Class<?> serviceClazz;
    String serviceBeanName;
    String repositoryBeanName;

    Binder(Class<?> clazz, String serviceName, String repositoryName) {
      serviceClazz = clazz;
      serviceBeanName = serviceName;
      repositoryBeanName = repositoryName;
    }

    public Class<?> getServiceClazz() {
      return serviceClazz;
    }

    public String getServiceBeanName() {
      return serviceBeanName;
    }

    public String getRepositoryBeanName() {
      return repositoryBeanName;
    }
  }
}
