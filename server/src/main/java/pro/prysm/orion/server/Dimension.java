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
        typeBuilder.putBoolean("piglin_safe", false);
        typeBuilder.putBoolean("natural", true);
        typeBuilder.putFloat("ambient_light", 0F);
        typeBuilder.putString("infiniburn", "minecraft:infiniburn_overworld");
        typeBuilder.putBoolean("respawn_anchor_works", false);
        typeBuilder.putBoolean("has_skylight", true);
        typeBuilder.putBoolean("bed_works", true);
        typeBuilder.putString("effects", "minecraft_overworld");
        typeBuilder.putBoolean("has_raids", true);
        typeBuilder.putInt("min_y", 0);
        typeBuilder.putInt("height", 256);
        typeBuilder.putInt("logical_height", 256);
        typeBuilder.putDouble("coordinate_scale", 1D);
        typeBuilder.putBoolean("ultrawarm", false);
        typeBuilder.putBoolean("has_ceiling", false);
        type = typeBuilder.build();
    }
}
