package pro.prysm.orion.server.data.dimension;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DimensionType {
    private String name;
    private int id;
    private boolean piglinSafe;
    private boolean natural;
    private float ambientLight;
    private String infiniburn;
    private boolean respawnAnchorWorks;
    private boolean hasSkylight;
    private boolean bedWorks;
    private String effects;
    private boolean hasRaids;
    private int minY;
    private int height;
    private int logicalHeight;
    private double coordinateScale;
    private boolean ultrawarm;
    private boolean hasCeiling;

    public DimensionType() {
    }

    public DimensionType(String name, int id, boolean piglinSafe, boolean natural, float ambientLight, String infiniburn, boolean respawnAnchorWorks, boolean hasSkylight, boolean bedWorks, String effects, boolean hasRaids, int minY, int height, int logicalHeight, double coordinateScale, boolean ultrawarm, boolean hasCeiling) {
        this.name = name;
        this.id = id;
        this.piglinSafe = piglinSafe;
        this.natural = natural;
        this.ambientLight = ambientLight;
        this.infiniburn = infiniburn;
        this.respawnAnchorWorks = respawnAnchorWorks;
        this.hasSkylight = hasSkylight;
        this.bedWorks = bedWorks;
        this.effects = effects;
        this.hasRaids = hasRaids;
        this.minY = minY;
        this.height = height;
        this.logicalHeight = logicalHeight;
        this.coordinateScale = coordinateScale;
        this.ultrawarm = ultrawarm;
        this.hasCeiling = hasCeiling;
    }
}