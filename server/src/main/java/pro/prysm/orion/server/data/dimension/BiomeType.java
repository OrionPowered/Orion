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

    public BiomeType() {
    }

    public BiomeType(String name, int id, String precipitation, long skyColor, long waterFogColor, long fogColor, long waterColor, int moodSoundTickDelay, double moodSoundOffset, String moodSoundSound, int moodSoundBlockSearchExtent, float depth, float temperature, float scale, float downfall, String category) {
        this.name = name;
        this.id = id;
        this.precipitation = precipitation;
        this.skyColor = skyColor;
        this.waterFogColor = waterFogColor;
        this.fogColor = fogColor;
        this.waterColor = waterColor;
        this.moodSoundTickDelay = moodSoundTickDelay;
        this.moodSoundOffset = moodSoundOffset;
        this.moodSoundSound = moodSoundSound;
        this.moodSoundBlockSearchExtent = moodSoundBlockSearchExtent;
        this.depth = depth;
        this.temperature = temperature;
        this.scale = scale;
        this.downfall = downfall;
        this.category = category;
    }
}