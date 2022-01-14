package pro.prysm.orion.api.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import pro.prysm.orion.api.util.CollectorUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
public class Biome {
    private static final List<Biome> REGISTRY = new ArrayList<>(Arrays.asList(
            // Begin auto generated code
            new Biome(0, "the_void", "none", 0.5, "none", 0.10000000149011612, "overworld", "The Void", 8103167, 0.5),
            new Biome(1, "plains", "plains", 0.800000011920929, "rain", 0.125, "overworld", "Plains", 7907327, 0.4000000059604645),
            new Biome(2, "sunflower_plains", "plains", 0.800000011920929, "rain", 0.125, "overworld", "Sunflower Plains", 7907327, 0.4000000059604645),
            new Biome(3, "snowy_plains", "icy", 0, "snow", 0, "overworld", "Snowy Plains", 8364543, 0.5),
            new Biome(4, "ice_spikes", "icy", 0, "snow", 0.42500001192092896, "overworld", "Ice Spikes", 8364543, 0.5),
            new Biome(5, "desert", "desert", 2, "none", 0.125, "overworld", "Desert", 7254527, 0),
            new Biome(6, "swamp", "swamp", 0.800000011920929, "rain", -0.20000000298023224, "overworld", "Swamp", 7907327, 0.8999999761581421),
            new Biome(7, "forest", "forest", 0.699999988079071, "rain", 0.10000000149011612, "overworld", "Forest", 7972607, 0.800000011920929),
            new Biome(8, "flower_forest", "forest", 0.699999988079071, "rain", 0.10000000149011612, "overworld", "Flower Forest", 7972607, 0.800000011920929),
            new Biome(9, "birch_forest", "forest", 0.6000000238418579, "rain", 0.10000000149011612, "overworld", "Birch Forest", 8037887, 0.6000000238418579),
            new Biome(10, "dark_forest", "forest", 0.699999988079071, "rain", 0.10000000149011612, "overworld", "Dark Forest", 7972607, 0.800000011920929),
            new Biome(11, "old_growth_birch_forest", "forest", 0.6000000238418579, "rain", 0, "overworld", "Old Growth Birch Forest", 8037887, 0.6000000238418579),
            new Biome(12, "old_growth_pine_taiga", "taiga", 0.30000001192092896, "rain", 0, "overworld", "Old Growth Pine Taiga", 8168447, 0.800000011920929),
            new Biome(13, "old_growth_spruce_taiga", "taiga", 0.25, "rain", 0, "overworld", "Old Growth Spruce Taiga", 8233983, 0.800000011920929),
            new Biome(14, "taiga", "taiga", 0.25, "rain", 0.20000000298023224, "overworld", "Taiga", 8233983, 0.800000011920929),
            new Biome(15, "snowy_taiga", "taiga", -0.5, "snow", 0.20000000298023224, "overworld", "Snowy Taiga", 8625919, 0.4000000059604645),
            new Biome(16, "savanna", "savanna", 2, "none", 0.125, "overworld", "Savanna", 7254527, 0),
            new Biome(17, "savanna_plateau", "savanna", 2, "none", 1.5, "overworld", "Savanna Plateau", 7254527, 0),
            new Biome(18, "windswept_hills", "extreme_hills", 0.20000000298023224, "rain", 0, "overworld", "Windswept Hills", 8233727, 0.30000001192092896),
            new Biome(19, "windswept_gravelly_hills", "extreme_hills", 0.20000000298023224, "rain", 0, "overworld", "Windswept Gravelly Hills", 8233727, 0.30000001192092896),
            new Biome(20, "windswept_forest", "extreme_hills", 0.20000000298023224, "rain", 0, "overworld", "Windswept Forest", 8233727, 0.30000001192092896),
            new Biome(21, "windswept_savanna", "savanna", 2, "none", 0, "overworld", "Windswept Savanna", 7254527, 0),
            new Biome(22, "jungle", "jungle", 0.949999988079071, "rain", 0.10000000149011612, "overworld", "Jungle", 7842047, 0.8999999761581421),
            new Biome(23, "sparse_jungle", "jungle", 0.949999988079071, "rain", 0, "overworld", "Sparse Jungle", 7842047, 0.800000011920929),
            new Biome(24, "bamboo_jungle", "jungle", 0.949999988079071, "rain", 0.10000000149011612, "overworld", "Bamboo Jungle", 7842047, 0.8999999761581421),
            new Biome(25, "badlands", "mesa", 2, "none", 0.10000000149011612, "overworld", "Badlands", 7254527, 0),
            new Biome(26, "eroded_badlands", "mesa", 2, "none", 0.10000000149011612, "overworld", "Eroded Badlands", 7254527, 0),
            new Biome(27, "wooded_badlands", "mesa", 2, "none", 0, "overworld", "Wooded Badlands", 7254527, 0),
            new Biome(28, "meadow", "mountain", 0.5, "rain", 0, "overworld", "Meadow", 8103167, 0.800000011920929),
            new Biome(29, "grove", "forest", -0.20000000298023224, "snow", 0, "overworld", "Grove", 8495359, 0.800000011920929),
            new Biome(30, "snowy_slopes", "mountain", -0.30000001192092896, "snow", 0, "overworld", "Snowy Slopes", 8560639, 0.8999999761581421),
            new Biome(31, "frozen_peaks", "mountain", -0.699999988079071, "snow", 0, "overworld", "Frozen Peaks", 8756735, 0.8999999761581421),
            new Biome(32, "jagged_peaks", "mountain", -0.699999988079071, "snow", 0, "overworld", "Jagged Peaks", 8756735, 0.8999999761581421),
            new Biome(33, "stony_peaks", "mountain", 1, "rain", 0, "overworld", "Stony Peaks", 7776511, 0.30000001192092896),
            new Biome(34, "river", "river", 0.5, "rain", -0.5, "overworld", "River", 8103167, 0.5),
            new Biome(35, "frozen_river", "river", 0, "snow", -0.5, "overworld", "Frozen River", 8364543, 0.5),
            new Biome(36, "beach", "beach", 0.800000011920929, "rain", 0, "overworld", "Beach", 7907327, 0.4000000059604645),
            new Biome(37, "snowy_beach", "beach", 0.05000000074505806, "snow", 0, "overworld", "Snowy Beach", 8364543, 0.30000001192092896),
            new Biome(38, "stony_shore", "beach", 0.20000000298023224, "rain", 0, "overworld", "Stony Shore", 8233727, 0.30000001192092896),
            new Biome(39, "warm_ocean", "ocean", 0.5, "rain", -1, "overworld", "Warm Ocean", 8103167, 0.5),
            new Biome(40, "lukewarm_ocean", "ocean", 0.5, "rain", -1, "overworld", "Lukewarm Ocean", 8103167, 0.5),
            new Biome(41, "deep_lukewarm_ocean", "ocean", 0.5, "rain", -1.7999999523162842, "overworld", "Deep Lukewarm Ocean", 8103167, 0.5),
            new Biome(42, "ocean", "ocean", 0.5, "rain", -1, "overworld", "Ocean", 8103167, 0.5),
            new Biome(43, "deep_ocean", "ocean", 0.5, "rain", -1.7999999523162842, "overworld", "Deep Ocean", 8103167, 0.5),
            new Biome(44, "cold_ocean", "ocean", 0.5, "rain", -1, "overworld", "Cold Ocean", 8103167, 0.5),
            new Biome(45, "deep_cold_ocean", "ocean", 0.5, "rain", -1.7999999523162842, "overworld", "Deep Cold Ocean", 8103167, 0.5),
            new Biome(46, "frozen_ocean", "ocean", 0, "snow", -1, "overworld", "Frozen Ocean", 8364543, 0.5),
            new Biome(47, "deep_frozen_ocean", "ocean", 0.5, "rain", -1.7999999523162842, "overworld", "Deep Frozen Ocean", 8103167, 0.5),
            new Biome(48, "mushroom_fields", "mushroom", 0.8999999761581421, "rain", 0.20000000298023224, "overworld", "Mushroom Fields", 7842047, 1),
            new Biome(49, "dripstone_caves", "underground", 0.800000011920929, "rain", 0.125, "overworld", "Dripstone Caves", 7907327, 0.4000000059604645),
            new Biome(50, "lush_caves", "underground", 0.5, "rain", 0.5, "overworld", "Lush Caves", 8103167, 0.5),
            new Biome(51, "nether_wastes", "nether", 2, "none", 0.10000000149011612, "nether", "Nether Wastes", 7254527, 0),
            new Biome(52, "warped_forest", "nether", 2, "none", 0.10000000149011612, "nether", "Warped Forest", 7254527, 0),
            new Biome(53, "crimson_forest", "nether", 2, "none", 0.10000000149011612, "nether", "Crimson Forest", 7254527, 0),
            new Biome(54, "soul_sand_valley", "nether", 2, "none", 0.10000000149011612, "nether", "Soul Sand Valley", 7254527, 0),
            new Biome(55, "basalt_deltas", "nether", 2, "none", 0.10000000149011612, "nether", "Basalt Deltas", 7254527, 0),
            new Biome(56, "the_end", "the_end", 0.5, "none", 0.10000000149011612, "end", "The End", 0, 0.5),
            new Biome(57, "end_highlands", "the_end", 0.5, "none", 0.10000000149011612, "end", "End Highlands", 0, 0.5),
            new Biome(58, "end_midlands", "the_end", 0.5, "none", 0.10000000149011612, "end", "End Midlands", 0, 0.5),
            new Biome(59, "small_end_islands", "the_end", 0.5, "none", 0.10000000149011612, "end", "Small End Islands", 0, 0.5),
            new Biome(60, "end_barrens", "the_end", 0.5, "none", 0.10000000149011612, "end", "End Barrens", 0, 0.5)
            // End auto generated code
    ));

    // Begin auto generated code
    public static final Biome THE_VOID = getBiome(0);
    public static final Biome PLAINS = getBiome(1);
    public static final Biome SUNFLOWER_PLAINS = getBiome(2);
    public static final Biome SNOWY_PLAINS = getBiome(3);
    public static final Biome ICE_SPIKES = getBiome(4);
    public static final Biome DESERT = getBiome(5);
    public static final Biome SWAMP = getBiome(6);
    public static final Biome FOREST = getBiome(7);
    public static final Biome FLOWER_FOREST = getBiome(8);
    public static final Biome BIRCH_FOREST = getBiome(9);
    public static final Biome DARK_FOREST = getBiome(10);
    public static final Biome OLD_GROWTH_BIRCH_FOREST = getBiome(11);
    public static final Biome OLD_GROWTH_PINE_TAIGA = getBiome(12);
    public static final Biome OLD_GROWTH_SPRUCE_TAIGA = getBiome(13);
    public static final Biome TAIGA = getBiome(14);
    public static final Biome SNOWY_TAIGA = getBiome(15);
    public static final Biome SAVANNA = getBiome(16);
    public static final Biome SAVANNA_PLATEAU = getBiome(17);
    public static final Biome WINDSWEPT_HILLS = getBiome(18);
    public static final Biome WINDSWEPT_GRAVELLY_HILLS = getBiome(19);
    public static final Biome WINDSWEPT_FOREST = getBiome(20);
    public static final Biome WINDSWEPT_SAVANNA = getBiome(21);
    public static final Biome JUNGLE = getBiome(22);
    public static final Biome SPARSE_JUNGLE = getBiome(23);
    public static final Biome BAMBOO_JUNGLE = getBiome(24);
    public static final Biome BADLANDS = getBiome(25);
    public static final Biome ERODED_BADLANDS = getBiome(26);
    public static final Biome WOODED_BADLANDS = getBiome(27);
    public static final Biome MEADOW = getBiome(28);
    public static final Biome GROVE = getBiome(29);
    public static final Biome SNOWY_SLOPES = getBiome(30);
    public static final Biome FROZEN_PEAKS = getBiome(31);
    public static final Biome JAGGED_PEAKS = getBiome(32);
    public static final Biome STONY_PEAKS = getBiome(33);
    public static final Biome RIVER = getBiome(34);
    public static final Biome FROZEN_RIVER = getBiome(35);
    public static final Biome BEACH = getBiome(36);
    public static final Biome SNOWY_BEACH = getBiome(37);
    public static final Biome STONY_SHORE = getBiome(38);
    public static final Biome WARM_OCEAN = getBiome(39);
    public static final Biome LUKEWARM_OCEAN = getBiome(40);
    public static final Biome DEEP_LUKEWARM_OCEAN = getBiome(41);
    public static final Biome OCEAN = getBiome(42);
    public static final Biome DEEP_OCEAN = getBiome(43);
    public static final Biome COLD_OCEAN = getBiome(44);
    public static final Biome DEEP_COLD_OCEAN = getBiome(45);
    public static final Biome FROZEN_OCEAN = getBiome(46);
    public static final Biome DEEP_FROZEN_OCEAN = getBiome(47);
    public static final Biome MUSHROOM_FIELDS = getBiome(48);
    public static final Biome DRIPSTONE_CAVES = getBiome(49);
    public static final Biome LUSH_CAVES = getBiome(50);
    public static final Biome NETHER_WASTES = getBiome(51);
    public static final Biome WARPED_FOREST = getBiome(52);
    public static final Biome CRIMSON_FOREST = getBiome(53);
    public static final Biome SOUL_SAND_VALLEY = getBiome(54);
    public static final Biome BASALT_DELTAS = getBiome(55);
    public static final Biome THE_END = getBiome(56);
    public static final Biome END_HIGHLANDS = getBiome(57);
    public static final Biome END_MIDLANDS = getBiome(58);
    public static final Biome SMALL_END_ISLANDS = getBiome(59);
    public static final Biome END_BARRENS = getBiome(60);

    // End auto generated code
    private final int id;
    private final String name;
    private final String category;
    private final double temperature;
    private final String precipitation;
    private final double depth;
    private final String dimension;
    private final String displayName;
    private final int color;
    private final double rainfall;

    public Biome(
            int id,
            String name,
            String category,
            double temperature,
            String precipitation,
            double depth,
            String dimension,
            String displayName,
            int color,
            double rainfall
    ) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.temperature = temperature;
        this.precipitation = precipitation;
        this.depth = depth;
        this.dimension = dimension;
        this.displayName = displayName;
        this.color = color;
        this.rainfall = rainfall;
    }

    /**
     * Gets a biome by its name
     *
     * @param name Biome Name
     * @return Biome
     */
    public static Biome getBiome(String name) {
        return REGISTRY.stream().filter(biome -> biome.getName().equalsIgnoreCase(name)).collect(CollectorUtil.toSingleton());
    }

    /**
     * Gets a biome by its ID
     *
     * @param id Biome ID
     * @return Biome
     */
    public static Biome getBiome(int id) {
        return REGISTRY.stream().filter(biome -> biome.getId() == id).collect(CollectorUtil.toSingleton());
    }
}