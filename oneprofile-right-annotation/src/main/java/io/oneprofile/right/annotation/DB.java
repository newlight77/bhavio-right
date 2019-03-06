package io.oneprofile.right.annotation;

import java.util.Arrays;
import java.util.List;

public enum DB {

  JPA(Arrays.asList(
      new Binder("jpa","HasRightJpaService", "hasRightService", "rightJpaRepository")
  )),

  CASSANDRA(Arrays.asList(
      new Binder("cassandra", "HasRightCassandraService", "hasRightService", "rightCassandraRepository")
  )),

  MONGO(Arrays.asList(
      new Binder("mongo","HasRightMongoService", "hasRightService", "rightMongoRepository")
  )),

  NEO4J(Arrays.asList(
      new Binder("neo4j", "HasRightNeo4JService", "hasRightService", "rightNeo4jRepository")
  ));

  List<Binder> binders;

  DB(List<Binder> list) {
    binders = list;
  }

  static class Binder {
    String name;
    Class<?> serviceClazz;
    String serviceBeanName;
    String repositoryBeanName;

    Binder(String name, String clazz, String serviceName, String repositoryName) {
      this.name = name;
      try {
        serviceClazz = this.getClass().getClassLoader().loadClass(clazz);
      } catch (ClassNotFoundException e) {
        System.out.println("It's ok! ClassNotFoundException for " + e.getMessage() + ". Make sur to inject the proper proper oneprofile-right-service-" + this.name);
      }
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
