package pro.prysm.orion.server;

import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;

public class Dimension {
    private CompoundBinaryTag codec;
    private CompoundBinaryTag type;

    public Dimension() {
        genCodec();
        genType();
    }

    public CompoundBinaryTag getCodec() {
        return codec;
    }

    public CompoundBinaryTag getType() {
        return type;
    }

    private void genCodec() {
        CompoundBinaryTag.Builder codecBuilder = CompoundBinaryTag.builder();
        CompoundBinaryTag.Builder dimensionType = CompoundBinaryTag.builder();
        dimensionType.putString("type", "minecraft:dimension_type");
        ListBinaryTag.Builder<BinaryTag> dimensionValues = ListBinaryTag.builder();
        CompoundBinaryTag.Builder overworld = CompoundBinaryTag.builder();
        overworld.putString("name", "minecraft:overworld");
        overworld.putInt("id", 0);

        overworld.put("element", desertElement());

        dimensionValues.add(overworld.build());
        dimensionType.put("value", dimensionValues.build());

        codecBuilder.put(dimensionType.build());
        CompoundBinaryTag.Builder worldGenBiome = CompoundBinaryTag.builder();
        worldGenBiome.putString("type", "minecraft:worldgen/biome");
        ListBinaryTag.Builder<BinaryTag> genValues = ListBinaryTag.builder();

        CompoundBinaryTag.Builder desert = CompoundBinaryTag.builder();
        desert.putString("type", "minecraft:desert");
        desert.putInt("id", 0);

        CompoundBinaryTag.Builder desertElement = CompoundBinaryTag.builder();
        desertElement.putString("precipitation", "none");

        CompoundBinaryTag.Builder desertEffects = CompoundBinaryTag.builder();
        desertEffects.putInt("sky_color", 7254527);
        desertEffects.putInt("water_fog_color", 329011);
        desertEffects.putInt("fog_color", 12638463);
        desertEffects.putInt("water_color", 4159204);
        desertElement.put("effects", desertEffects.build());

        desertElement.putFloat("depth", 0.125F);
        desertElement.putFloat("temperature", 2.0F);
        desertElement.putFloat("downfall", 0.0F);
        desertElement.putString("category", "desert");
        desert.put(desertElement.build());

        genValues.add(desert.build());
        worldGenBiome.put("value", genValues.build());
        codecBuilder.put(worldGenBiome.build());
        codec = codecBuilder.build();
    }

    private void genType() {
        CompoundBinaryTag.Builder typeBuilder = CompoundBinaryTag.builder();
        typeBuilder.putString("type", "minecraft:overworld");
        typeBuilder.putInt("id", 0);
        typeBuilder.put("element", desertElement());
        type = typeBuilder.build();
    }

    private CompoundBinaryTag desertElement() {
        CompoundBinaryTag.Builder element = CompoundBinaryTag.builder();
        element.putBoolean("piglin_safe", false);
        element.putBoolean("natural", true);
        element.putFloat("ambient_light", 0F);
        element.putString("infiniburn", "minecraft:infiniburn_overworld");
        element.putBoolean("respawn_anchor_works", false);
        element.putBoolean("has_skylight", true);
        element.putBoolean("bed_works", true);
        element.putString("effects", "minecraft_overworld");
        element.putBoolean("has_raids", true);
        element.putInt("min_y", 0);
        element.putInt("height", 256);
        element.putInt("logical_height", 256);
        element.putDouble("coordinate_scale", 1D);
        element.putBoolean("ultrawarm", false);
        element.putBoolean("has_ceiling", false);
        return element.build();
    }

}
