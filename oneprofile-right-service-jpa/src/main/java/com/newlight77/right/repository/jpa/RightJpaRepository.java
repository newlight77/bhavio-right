package com.newlight77.right.repository.jpa;

import com.newlight77.right.entity.jpa.RightJpaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface RightJpaRepository extends CrudRepository<RightJpaEntity, String> {

  Collection<RightJpaEntity> findByPrimaryAndSecondary(String primary, String secondary);
}
