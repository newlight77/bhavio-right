package io.oneprofile.right.demo.repository;

import io.oneprofile.right.demo.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface UserRepository extends Neo4jRepository<UserEntity, String> {

  Page<UserEntity> findAll(Pageable pageable);

  Collection<UserEntity> findByUsername(@Param("username") String username);

  Collection<UserEntity> findByFirstnameAndLastname(@Param("firstname") String firstname,
                                                    @Param("lastname") String lastname);
}
