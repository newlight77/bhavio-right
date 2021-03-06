package io.oneprofile.right.demo;

import com.google.common.collect.Sets;
import io.oneprofile.right.entity.neo4j.RightNeo4jEntity;
import io.oneprofile.right.model.Right;
import io.oneprofile.right.repository.neo4j.RightNeo4jRepository;
import io.oneprofile.right.entity.neo4j.RightNeo4jEntity;
import io.oneprofile.right.model.Right;
import io.oneprofile.right.repository.neo4j.RightNeo4jRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

public class RightsInjectionRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(RightsInjectionRunner.class);

    private RightNeo4jRepository rightRepository;

    public RightsInjectionRunner(RightNeo4jRepository rightRepository) {
        this.rightRepository = rightRepository;
    }

    @Override
    public void run(final String... args) throws Exception {
        RightNeo4jEntity entity11 = RightNeo4jEntity.builder()
                .primary("newlight77@gmail.com")
                .secondary("UserService")
                .rights(Sets.newHashSet(Right.ADMIN_READ, Right.ADMIN_WRITE))
                .build();
        rightRepository.save(entity11);
        RightNeo4jEntity entity12 = RightNeo4jEntity.builder()
                .primary("newlight77@gmail.com")
                .secondary("RightCrudService")
                .rights(Sets.newHashSet(Right.ADMIN_READ, Right.ADMIN_WRITE))
                .build();
        rightRepository.save(entity12);
    }
}
