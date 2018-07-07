package com.newlight77.right.demo.service;

import com.newlight77.right.aspect.Rights;
import com.newlight77.right.demo.mapper.RightMapper;
import com.newlight77.right.demo.model.RightDto;
import com.newlight77.right.entity.neo4j.RightNeo4jEntity;
import com.newlight77.right.model.Right;
import com.newlight77.right.repository.neo4j.RightNeo4jRepository;
import com.newlight77.right.service.RightFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RightCrudService {

    private RightNeo4jRepository rightRepository;

    public RightCrudService(RightNeo4jRepository rightRepository) {
        this.rightRepository = rightRepository;
    }

    @Rights(rights = Right.ADMIN_WRITE)
    public RightDto save(String primary, RightDto dto) {
        RightNeo4jEntity entity = RightMapper.from(dto);
        return RightMapper.to(rightRepository.save(entity));
    }

    @Rights(rights = Right.ADMIN_READ)
    public Page<RightDto> findAll(String primary, Pageable pageable) {
        return rightRepository.findAll(pageable)
                .map(RightMapper::to);
    }

    public boolean hasRight(String primary, RightFilter filter) {
        Set<Right> rights = new HashSet<>();
        Collection<RightNeo4jEntity> entities =
                rightRepository.findByPrimaryAndSecondary(filter.getPrimary(), filter.getSecondary(), 100);
        entities.forEach(entity -> {
            rights.addAll(entity.getRights());
        });
        rights.removeAll(filter.getRights());
        return !rights.isEmpty();
    }
}
