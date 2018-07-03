package com.newlight77.right.repository.jpa;

import com.newlight77.right.entity.jpa.RightJpaEntity;
import org.springframework.data.repository.CrudRepository;

public interface RightJpaRepository extends CrudRepository<RightJpaEntity, String> {

  Iterable<RightJpaEntity> findByPrimaryAndSecondary(String primary, String secondary);
}
