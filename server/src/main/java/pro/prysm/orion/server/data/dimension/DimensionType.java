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
}