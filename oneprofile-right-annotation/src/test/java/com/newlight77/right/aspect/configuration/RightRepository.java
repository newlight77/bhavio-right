package com.newlight77.right.aspect.configuration;

import java.util.Collection;

public interface RightRepository {
    public Collection<RightEntity> findByPrimaryAndSecondary(String primary, String secondary, int limit);
}
