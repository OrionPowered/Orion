package pro.prysm.orion.server;

import net.kyori.adventure.nbt.CompoundBinaryTag;

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

        codecBuilder.put(dimensionType.build());
        CompoundBinaryTag.Builder worldGenBiome = CompoundBinaryTag.builder();

        codecBuilder.put(worldGenBiome.build());
        codec = codecBuilder.build();
    }

    private void genType() {
        CompoundBinaryTag.Builder typeBuilder = CompoundBinaryTag.builder();
        typeBuilder.putString("type", "minecraft:overworld");
        typeBuilder.putInt("id", 0);
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
        typeBuilder.put("element", element.build());
        type = typeBuilder.build();
    }
}
