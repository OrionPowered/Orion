package pro.prysm.orion.server.data.dimension;

import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;

public class DimensionBuilder {

    // Codec
    CompoundBinaryTag codec;
    CompoundBinaryTag.Builder codecBuilder;

    // Dimension Type
    CompoundBinaryTag.Builder dimensionType;
    ListBinaryTag.Builder<BinaryTag> dimensionTypeValues;

    // Worldgen/biome
    CompoundBinaryTag.Builder worldGenBiome;
    ListBinaryTag.Builder<BinaryTag> worldGenBiomeTypeValues;

    public DimensionBuilder() {
        codecBuilder = CompoundBinaryTag.builder();
        dimensionType = CompoundBinaryTag.builder();
        dimensionTypeValues = ListBinaryTag.builder();
        worldGenBiome = CompoundBinaryTag.builder();
        worldGenBiomeTypeValues = ListBinaryTag.builder();
        dimensionType.putString("type", "minecraft:dimension_type");
        worldGenBiome.putString("type", "minecraft:worldgen/biome");
    }

    public void addTypeValue(DimensionType type) {
        CompoundBinaryTag.Builder tag = CompoundBinaryTag.builder();
        tag.putString("name", type.getName());
        tag.putInt("id", type.getId());
        CompoundBinaryTag.Builder element = CompoundBinaryTag.builder();
        element.putBoolean("piglin_safe", type.isPiglinSafe());
        element.putBoolean("natural", type.isNatural());
        element.putFloat("ambient_light", type.getAmbientLight());
        element.putString("infiniburn", type.getInfiniburn());
        element.putBoolean("respawn_anchor_works", type.isRespawnAnchorWorks());
        element.putBoolean("has_skylight", type.isHasSkylight());
        element.putBoolean("bed_works", type.isBedWorks());
        element.putString("effects", type.getEffects());
        element.putBoolean("has_raids", type.isHasRaids());
        element.putInt("min_y", type.getMinY());
        element.putInt("height", type.getHeight());
        element.putInt("logical_height", type.getLogicalHeight());
        element.putDouble("coordinate_scale", type.getCoordinateScale());
        element.putBoolean("ultrawarm", type.isUltrawarm());
        element.putBoolean("has_ceiling", type.isHasCeiling());
        tag.put("element", element.build());
        dimensionTypeValues.add(tag.build());
    }

    public void addBiome(BiomeType biome) {
        CompoundBinaryTag.Builder tag = CompoundBinaryTag.builder();
        tag.putString("name", biome.getName());
        tag.putInt("id", biome.getId());
        CompoundBinaryTag.Builder element = CompoundBinaryTag.builder();
        CompoundBinaryTag.Builder effects = CompoundBinaryTag.builder();
        element.putString("precipitation", biome.getPrecipitation());
        effects.putLong("sky_color", biome.getSkyColor());
        effects.putLong("fog_color", biome.getFogColor());
        effects.putLong("water_fog_color", biome.getWaterColor());
        effects.putLong("water_color", biome.getWaterColor());
        CompoundBinaryTag.Builder moodSound = CompoundBinaryTag.builder();
        moodSound.putInt("tick_delay", biome.getMoodSoundTickDelay());
        moodSound.putDouble("offset", biome.getMoodSoundOffset());
        moodSound.putString("sound", biome.getMoodSoundSound());
        moodSound.putInt("biome_search_extent", biome.getMoodSoundBlockSearchExtent());
        effects.put("mood_sound", moodSound.build());
        element.put("effects", effects.build());
        element.putFloat("depth", biome.getDepth());
        element.putFloat("temperature", biome.getTemperature());
        element.putFloat("scale", biome.getScale());
        element.putFloat("downfall", biome.getDownfall());
        element.putString("category", biome.getCategory());
        tag.put("element", element.build());
        worldGenBiomeTypeValues.add(tag.build());
    }

    public CompoundBinaryTag build() {
        dimensionType.put("value", dimensionTypeValues.build());
        worldGenBiome.put("value", worldGenBiomeTypeValues.build());
        codecBuilder.put("minecraft:dimension_type", dimensionType.build());
        codecBuilder.put("minecraft:worldgen/biome", worldGenBiome.build());
        codec = codecBuilder.build();
        return codec;
    }
}
