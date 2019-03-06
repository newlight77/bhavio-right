package io.oneprofile.right.aspect.stub;

import io.oneprofile.right.model.Right;
import io.oneprofile.right.model.TemporaryRight;
import io.oneprofile.right.model.Right;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class RightEntity {
    private String primary;
    private String secondary;
    private Set<Right> rights;
    private Set<TemporaryRight> tempRights = new HashSet<>();
    private Instant modificationDate = Instant.now();

    public RightEntity(String primary, String secondary, Set<Right> rights) {
        this.primary = primary;
        this.secondary = secondary;
        this.rights = rights;
    }

    public Set<Right> getRights() {
        return rights;
    }

}
