package com.newlight77.right.aspect.stub;

import com.newlight77.right.model.Right;
import com.newlight77.right.service.HasRightService;
import com.newlight77.right.service.RightFilter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RightServiceStub implements HasRightService {
    private RightRepository rightRepository;

    public RightServiceStub(RightRepository rightRepository) {
        this.rightRepository = rightRepository;
    }

    @Override
    public boolean hasRight(RightFilter filter) {
        Set<Right> rights = new HashSet<>();
        Collection<RightEntity> entities =
                rightRepository.findByPrimaryAndSecondary(filter.getPrimary(), filter.getSecondary(), 100);
        entities.forEach(entity -> {
            rights.addAll(entity.getRights());
        });

        for ( Right right: filter.getRights() ) {
            if (rights.contains(right)) {
                // if database contains at least one right allowed by method
                return true;
            }
        }

        return false;
    }
}
