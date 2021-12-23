package pro.prysm.orion.server.data;

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

        codecBuilder.put("minecraft:dimension_type", genDimensionType());
        codecBuilder.put("minecraft:worldgen/biome", genWorldGenBiome());

        codec = codecBuilder.build();
    }

    private void genType() {
        CompoundBinaryTag.Builder typeBuilder = CompoundBinaryTag.builder();
        typeBuilder.putString("name", "minecraft:overworld");
        typeBuilder.putInt("id", 0);
        typeBuilder.put(plainsElement());
        type = typeBuilder.build();
    }

    private CompoundBinaryTag plainsElement() {
        CompoundBinaryTag.Builder element = CompoundBinaryTag.builder();
        element.putBoolean("piglin_safe", false);
        element.putBoolean("natural", true);
        element.putFloat("ambient_light", 0.0F);
        element.putString("infiniburn", "minecraft:infiniburn_overworld");
        element.putBoolean("respawn_anchor_works", false);
        element.putBoolean("has_skylight", true);
        element.putBoolean("bed_works", true);
        element.putString("effects", "minecraft_overworld");
        element.putBoolean("has_raids", true);
        element.putInt("min_y", 0);
        element.putInt("height", 256);
        element.putInt("logical_height", 256);
        element.putDouble("coordinate_scale", 1.0D);
        element.putBoolean("ultrawarm", false);
        element.putBoolean("has_ceiling", false);
        return element.build();
    }

    private CompoundBinaryTag genDimensionType() {
        CompoundBinaryTag.Builder out = CompoundBinaryTag.builder();
        CompoundBinaryTag.Builder dimensionType = CompoundBinaryTag.builder();
        dimensionType.putString("type", "minecraft:dimension_type");
        ListBinaryTag.Builder<BinaryTag> dimensionValues = ListBinaryTag.builder();
        CompoundBinaryTag.Builder overworld = CompoundBinaryTag.builder();
        overworld.putString("name", "minecraft:overworld");
        overworld.putInt("id", 0);

        overworld.put("element", plainsElement());

        dimensionValues.add(overworld.build());
        dimensionType.put("value", dimensionValues.build());

        out.put(dimensionType.build());
        return out.build();
    }

    private CompoundBinaryTag genWorldGenBiome() {
        CompoundBinaryTag.Builder out = CompoundBinaryTag.builder();
        out.putString("type", "minecraft:worldgen/biome");
        ListBinaryTag.Builder<BinaryTag> genValues = ListBinaryTag.builder();

        CompoundBinaryTag.Builder plains = CompoundBinaryTag.builder();
        plains.putString("name", "minecraft:plains");
        plains.putInt("id", 0);

        CompoundBinaryTag.Builder plainsElement = CompoundBinaryTag.builder();
        plainsElement.putString("precipitation", "none");

        CompoundBinaryTag.Builder plainsEffects = CompoundBinaryTag.builder();
        plainsEffects.putInt("sky_color", 7254527);
        plainsEffects.putInt("water_fog_color", 329011);
        plainsEffects.putInt("fog_color", 12638463);
        plainsEffects.putInt("water_color", 4159204);
        plainsElement.put("effects", plainsEffects.build());

        plainsElement.putFloat("depth", 0.125F);
        plainsElement.putFloat("temperature", 2.0F);
        plainsElement.putFloat("downfall", 0.0F);
        plainsElement.putString("category", "plains");
        plains.put("element", plainsElement.build());

        genValues.add(plains.build());
        out.put("value", genValues.build());
        return out.build();
    }

}
