package pro.prysm.orion.server.protocol.outgoing.play;

import lombok.Setter;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import pro.prysm.orion.api.entity.player.GameMode;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.Server;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;
import pro.prysm.orion.server.world.LevelProvider;
import pro.prysm.orion.server.world.dimension.DimensionProvider;

/**
 * @author 254n_m
 * @since 12/21/21 / 9:52 PM
 * This file was created as a part of Orion
 */
@Setter
public class JoinGame extends OutgoingPacket {
    private int entityId;
    private boolean hardcore;
    private GameMode gamemode;
    private GameMode previousGamemode;
    private String[] worlds;
    private CompoundBinaryTag dimensionCodec;
    private CompoundBinaryTag dimension;
    private String worldName;
    private long hashedSeed;
    private int maxPlayers;
    private int viewDistance;
    private int simulationDistance;
    private boolean reducedDebugInfo;
    private boolean respawnScreen;
    private boolean debug;
    private boolean flat;

    public JoinGame() {
        super(0x26);

        // Set default values
        Server server = Orion.getServer();
        LevelProvider level = server.getLevelProvider();
        DimensionProvider dimensionProvider = level.getDimensionProvider();
        dimensionCodec = dimensionProvider.getDimension();
        worlds = level.getWorldNames();
        maxPlayers = server.getMaxPlayers();
        viewDistance = server.getRenderDistance();
        simulationDistance = server.getSimulationDistance();
        reducedDebugInfo = false;
        respawnScreen = true;
        debug = false;
        flat = false;
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeInt(entityId);
        buf.writeBoolean(hardcore);
        buf.writeByte(gamemode.getId());
        buf.writeByte(previousGamemode.getId());
        buf.writeVarInt(worlds.length);
        for (String w : worlds) buf.writeString(w);
        buf.writeNBT(dimensionCodec);
        buf.writeNBT(dimension);
        buf.writeString(worldName);
        buf.writeLong(hashedSeed);
        buf.writeVarInt(maxPlayers);
        buf.writeVarInt(viewDistance);
        buf.writeVarInt(simulationDistance);
        buf.writeBoolean(reducedDebugInfo);
        buf.writeBoolean(respawnScreen);
        buf.writeBoolean(debug);
        buf.writeBoolean(flat);
    }
}
