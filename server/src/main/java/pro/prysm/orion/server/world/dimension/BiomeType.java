package pro.prysm.orion.server.world.dimension;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
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