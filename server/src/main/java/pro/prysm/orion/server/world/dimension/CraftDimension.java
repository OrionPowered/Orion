package pro.prysm.orion.server.world.dimension;

import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.nbt.BinaryTagIO;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import pro.prysm.orion.api.exception.ResourceNotFoundException;
import pro.prysm.orion.api.util.CollectorUtil;
import pro.prysm.orion.common.OrionExceptionHandler;
import pro.prysm.orion.server.Orion;

import java.io.IOException;
import java.io.InputStream;

// Vanilla implementation of the dimension codec

@Getter
@Setter
public class CraftDimension implements DimensionProvider {
    private CompoundBinaryTag dimension;

    public CraftDimension() {
        try {
            InputStream is = Orion.class.getClassLoader().getResourceAsStream("dimension_codec.nbt");
            if (is == null) throw new ResourceNotFoundException("Could not find dimension codec in jar!");
            dimension = BinaryTagIO.reader().read(is);
            Orion.getLogger().debug("Loaded dimension codec");
        } catch (IOException | ResourceNotFoundException e) {
            OrionExceptionHandler.error(e);
        }
    }

    public CompoundBinaryTag getDimensionType(Dimension dimension) {
        if (this.dimension != null) {
            CompoundBinaryTag dim = (CompoundBinaryTag) this.dimension.getCompound("minecraft:dimension_type").getList("value").stream().filter(tag -> ((CompoundBinaryTag) tag).getString("name").equals(dimension.getName())).collect(CollectorUtil.toSingleton());
            CompoundBinaryTag element = dim.getCompound("element").remove("element");
            CompoundBinaryTag.Builder result = CompoundBinaryTag.builder();
            result.put(dim);
            result.put(element);
            return result.build();
        } else throw new NullPointerException("Codec must be built before attempting to get a dimension!");
    }

    public CompoundBinaryTag getBiomeType(String name) {
        if (dimension != null) {
            return (CompoundBinaryTag) dimension.getCompound("minecraft:worldgen/biome").getList("value").stream().filter(tag -> ((CompoundBinaryTag) tag).getString("name").equals(name)).collect(CollectorUtil.toSingleton());
        } else throw new NullPointerException("Codec must be built before attempting to get a biome!");
    }
}
