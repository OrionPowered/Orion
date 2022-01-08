package pro.prysm.orion.server.entity;

import lombok.Data;
import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.api.entity.Entity;

@Data
public class ImplEntity implements Entity {
    private static int nextEntityId;

    private int entityId;
    private Location location;

    public int peekNextEntityId() {
        return nextEntityId;
    }

    public int useEntityId() {
        nextEntityId++;
        return nextEntityId - 1;
    }
}
