package pro.prysm.orion.server.protocol.handler;

import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.protocol.Protocol;
import pro.prysm.orion.server.protocol.incoming.status.*;
import pro.prysm.orion.server.protocol.incoming.login.*;
import pro.prysm.orion.server.protocol.incoming.play.*;

public abstract class ProtocolHandler {
    protected final Connection connection;
    protected final Protocol protocol;

    public ProtocolHandler(Connection connection) {
        this.connection = connection;
        this.protocol = connection.getProtocol();
    }

    public void handle(Handshake packet) { }

    public void handle(Ping packet) { }

    public void handle(EncryptionResponse packet) { }

    public void handle(LoginStart packet) { }

    public void handle(ClientSettings packet) { }

    public void handle(PluginMessage packet) { }

    public void handle(PlayerPosition packet) { }

    public void handle(PlayerRotation packet) { }

    public void handle(PlayerPositionAndRotation packet) { }

    public void handle(TeleportConfirm packet) { }
}
