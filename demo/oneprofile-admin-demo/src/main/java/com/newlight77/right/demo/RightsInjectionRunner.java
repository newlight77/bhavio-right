package com.newlight77.right.demo;

import com.google.common.collect.Sets;
//import com.newlight77.right.entity.neo4j.RightNeo4jEntity;
//import com.newlight77.right.model.Right;
//import com.newlight77.right.repository.neo4j.RightNeo4jRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

public class RightsInjectionRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(RightsInjectionRunner.class);

//    private RightNeo4jRepository rightRepository;

//    public RightsInjectionRunner(RightNeo4jRepository rightRepository) {
//        this.rightRepository = rightRepository;
//    }

    @Override
    public void run(final String... args) throws Exception {
//        RightNeo4jEntity entity1 = RightNeo4jEntity.builder()
//                .primary("master_user")
//                .secondary("core_resource")
//                .rights(Sets.newHashSet(Right.ADMIN_READ, Right.ADMIN_WRITE))
//                .build();
//        rightRepository.save(entity1);
//        RightNeo4jEntity entity2 = RightNeo4jEntity.builder()
//                .primary("master_user")
//                .secondary("admin_resource")
//                .rights(Sets.newHashSet(Right.ADMIN_READ, Right.ADMIN_WRITE))
//                .build();
//        rightRepository.save(entity2);
//
//        RightNeo4jEntity entity11 = RightNeo4jEntity.builder()
//                .primary("master_user")
//                .secondary("core_resource")
//                .rights(Sets.newHashSet(Right.ADMIN_READ, Right.ADMIN_WRITE))
//                .build();
//        rightRepository.save(entity11);
//        RightNeo4jEntity entity22 = RightNeo4jEntity.builder()
//                .primary("master_user")
//                .secondary("admin_resource")
//                .rights(Sets.newHashSet(Right.ADMIN_READ, Right.ADMIN_WRITE))
//                .build();
////        rightRepository.save(entity22);
    }
}
