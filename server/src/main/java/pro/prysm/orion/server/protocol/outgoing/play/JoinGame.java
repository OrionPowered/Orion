package pro.prysm.orion.server.protocol.outgoing.play;

import io.netty.buffer.ByteBuf;
import net.kyori.nbt.CompoundTag;
import pro.prysm.orion.api.protocol.outgoing.OutgoingPacket;
import pro.prysm.orion.server.protocol.GameProfile;

/**
 * @author 254n_m
 * @since 12/21/21 / 9:52 PM
 * This file was created as a part of Orion
 */
public class JoinGame extends pro.prysm.orion.server.protocol.outgoing.OutgoingPacket implements OutgoingPacket {
    private final GameProfile profile;

    public JoinGame(GameProfile profile) {
        id = 0x26;
        this.profile = profile;
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(0);
        buf.writeBoolean(false);
        buf.writeByte(1);
        buf.writeByte(1);
        writeVarInt(1, buf);
        writeString("world", buf);
        writeNBT(genTag(), buf);
        writeNBT(genDimensionType(), buf);
        writeString("world", buf);
        buf.writeLong(0L);
        writeVarInt(100, buf);
        writeVarInt(5,buf);
        buf.writeBoolean(false);
        buf.writeBoolean(true);
        buf.writeBoolean(false);
        buf.writeBoolean(false);

    }

    private CompoundTag genTag() {
        CompoundTag tag = new CompoundTag();
        tag.putString("name", "minecraft:desert");
        tag.putInt("id", 2);
        CompoundTag element = new CompoundTag();
        element.putString("precipitation", "none");
        CompoundTag effects = new CompoundTag();
        effects.putInt("sky_color", 7254527);
        effects.putInt("water_fog_color", 329011);
        effects.putInt("fog_color", 12638463);
        effects.putInt("water_color", 4159204);
        element.put("effects", effects);
        element.putFloat("depth", 0.125F);
        element.putFloat("temperature", 2.0F);
        element.putFloat("scale", 0.05F);
        element.putFloat("downfall", 0.0F);
        element.putString("category", "desert");
        tag.put("element", element);
        return tag;
    }
    private CompoundTag genDimensionType() {
        CompoundTag main = new CompoundTag();
        main.putString("name", "minecraft:overworld");
        main.putInt("id", 0);
        CompoundTag element = new CompoundTag();
        element.putByte("piglin_safe", (byte) 0);
        element.putByte("natural", (byte) 1);
        element.putFloat("ambient_light", 0.0F);
        element.putString("infiniburn", "minecraft:infiniburn_overworld");
        element.putByte("respawn_anchor_works", (byte) 0);
        element.putByte("has_skylight", (byte) 1);
        element.putByte("bed_works", (byte) 1);
        element.putString("effects", "minecraft:overworld");
        element.putByte("has_raids", (byte) 1);
        element.putInt("min_y", 0);
        element.putInt("height", 256);
        element.putInt("logical_height", 256);
        element.putFloat("coordinate_scale", 1.0F);
        element.putByte("ultrawarm", (byte) 0);
        element.putByte("has_ceiling", (byte) 0);
        main.put("element", element);
        return main;
    }
}
