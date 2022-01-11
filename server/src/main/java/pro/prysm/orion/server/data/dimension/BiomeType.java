package pro.prysm.orion.server.data.dimension;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BiomeType {
    private String name;
    private int id;
    private String precipitation;
    private long skyColor;
    private long waterFogColor;
    private long fogColor;
    private long waterColor;
    private int moodSoundTickDelay;
    private double moodSoundOffset;
    private String moodSoundSound;
    private int moodSoundBlockSearchExtent;
    private float depth;
    private float temperature;
    private float scale;
    private float downfall;
    private String category;
}