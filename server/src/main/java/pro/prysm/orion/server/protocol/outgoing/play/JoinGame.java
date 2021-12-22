package pro.prysm.orion.server.protocol.outgoing.play;

import io.netty.buffer.ByteBuf;
import pro.prysm.orion.api.protocol.outgoing.OutgoingPacket;
import pro.prysm.orion.server.GameMode;

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
    private Object dimensionCodec;
    private Object dimension;
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

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(entityId);
        buf.writeBoolean(hardcore);
        buf.writeByte(1);   // Gamemode
        buf.writeByte(1);   // Previous Gamemode
        writeVarInt(worlds.length, buf);
        for (String w : worlds) {
            writeString(w, buf);
        }
        // NBT
        // dimensionCodec
        // dimension
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
