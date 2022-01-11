package pro.prysm.orion.server.data.dimension;

import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.nbt.CompoundBinaryTag;

import java.util.stream.Collector;
import java.util.stream.Collectors;

@Getter
@Setter
public class Dimension {

    private CompoundBinaryTag dimension;

    public Dimension() {
        DimensionBuilder builder = new DimensionBuilder();
        DimensionType overworld = new DimensionType();

        // Overworld
        overworld.setName("minecraft:overworld");
        overworld.setId(0);
        overworld.setPiglinSafe(false);
        overworld.setAmbientLight(0.0F);
        overworld.setInfiniburn("minecraft:infiniburn_overworld");
        overworld.setRespawnAnchorWorks(false);
        overworld.setHasSkylight(true);
        overworld.setBedWorks(true);
        overworld.setEffects("minecraft:overworld");
        overworld.setHasRaids(true);
        overworld.setMinY(0);
        overworld.setHeight(320);
        overworld.setLogicalHeight(320);
        overworld.setUltrawarm(false);
        overworld.setCoordinateScale(1.0D);
        overworld.setHasCeiling(false);

        builder.addTypeValue(overworld);

        // Plains biome
        BiomeType plains = new BiomeType();
        plains.setName("minecraft:plains");
        plains.setId(1);
        plains.setPrecipitation("rain");
        plains.setSkyColor(7907327);
        plains.setWaterFogColor(329011);
        plains.setFogColor(12638463);
        plains.setWaterColor(4159204);
        plains.setMoodSoundTickDelay(6000);
        plains.setMoodSoundOffset(2.0D);
        plains.setMoodSoundSound("minecraft:ambient.cave");
        plains.setMoodSoundBlockSearchExtent(8);
        plains.setDepth(0.125F);
        plains.setTemperature(0.8F);
        plains.setScale(0.05F);
        plains.setDownfall(0.4F);
        plains.setCategory("plains");

        builder.addBiome(plains);
        dimension = builder.build();
    }

    public static <T> Collector<T, ?, T> toSingleton() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() == 0) throw new NullPointerException();
                    else if (list.size() > 1) throw new IllegalStateException();
                    else return list.get(0);
                }
        );
    }

    public CompoundBinaryTag getDimensionType(String name) {
        if (dimension != null) {
            CompoundBinaryTag dim = (CompoundBinaryTag) dimension.getCompound("minecraft:dimension_type").getList("value").stream().filter(tag -> ((CompoundBinaryTag) tag).getString("name").equals(name)).collect(toSingleton());
            CompoundBinaryTag element = dim.getCompound("element").remove("element");
            CompoundBinaryTag.Builder result = CompoundBinaryTag.builder();
            result.put(dim);
            result.put(element);
            return result.build();
        }
        throw new NullPointerException("Codec must be built before attempting to get a dimension!");
    }

    public CompoundBinaryTag getBiomeType(String name) {
        if (dimension != null) {
            return (CompoundBinaryTag) dimension.getCompound("minecraft:worldgen/biome").getList("value").stream().filter(tag -> ((CompoundBinaryTag) tag).getString("name").equals(name)).collect(toSingleton());
        }
        throw new NullPointerException("Codec must be built before attempting to get a biome!");
    }
}
