package pro.prysm.orion.server.protocol.outgoing.play;

import io.netty.buffer.ByteBuf;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import pro.prysm.orion.api.protocol.outgoing.OutgoingPacket;
import pro.prysm.orion.api.data.GameMode;

/**
 * @author 254n_m
 * @since 12/21/21 / 9:52 PM
 * This file was created as a part of Orion
 */
public class JoinGame extends pro.prysm.orion.server.protocol.outgoing.OutgoingPacket implements OutgoingPacket {
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
        id = 0x26;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public void setHardcore(boolean hardcore) {
        this.hardcore = hardcore;
    }

    public void setGamemode(GameMode gamemode) {
        this.gamemode = gamemode;
    }

    public void setPreviousGamemode(GameMode previousGamemode) {
        this.previousGamemode = previousGamemode;
    }

    public void setWorlds(String[] worlds) {
        this.worlds = worlds;
    }

    public void setDimensionCodec(CompoundBinaryTag dimensionCodec) {
        this.dimensionCodec = dimensionCodec;
    }

    public void setDimension(CompoundBinaryTag dimension) {
        this.dimension = dimension;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    public void setHashedSeed(long hashedSeed) {
        this.hashedSeed = hashedSeed;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public void setViewDistance(int viewDistance) {
        this.viewDistance = viewDistance;
    }

    public void setSimulationDistance(int simulationDistance) {
        this.simulationDistance = simulationDistance;
    }

    public void setReducedDebugInfo(boolean reducedDebugInfo) {
        this.reducedDebugInfo = reducedDebugInfo;
    }

    public void setRespawnScreen(boolean respawnScreen) {
        this.respawnScreen = respawnScreen;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void setFlat(boolean flat) {
        this.flat = flat;
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(entityId);
        buf.writeBoolean(hardcore);
        buf.writeByte(gamemode.getId());
        buf.writeByte(previousGamemode.getId());
        writeVarInt(worlds.length, buf);
        for (String w : worlds) writeString(w, buf);
        writeNBT(dimensionCodec, buf);
        writeNBT(dimension, buf);
        writeString(worldName, buf);
        buf.writeLong(hashedSeed);
        writeVarInt(maxPlayers, buf);
        writeVarInt(viewDistance, buf);
        writeVarInt(simulationDistance, buf);
        buf.writeBoolean(reducedDebugInfo);
        buf.writeBoolean(respawnScreen);
        buf.writeBoolean(debug);
        buf.writeBoolean(flat);
    }
}
