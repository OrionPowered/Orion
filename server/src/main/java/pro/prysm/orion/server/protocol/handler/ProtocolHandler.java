package pro.prysm.orion.server.protocol.handler;

import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.protocol.incoming.login.EncryptionResponse;
import pro.prysm.orion.server.protocol.incoming.login.LoginStart;
import pro.prysm.orion.server.protocol.incoming.play.TeleportConfirm;
import pro.prysm.orion.server.protocol.incoming.play.*;
import pro.prysm.orion.server.protocol.incoming.handshaking.Handshake;
import pro.prysm.orion.server.protocol.incoming.status.Ping;
import pro.prysm.orion.server.protocol.incoming.status.Request;

public abstract class ProtocolHandler {
    protected final Connection connection;

    public ProtocolHandler(Connection connection) {
        this.connection = connection;
    }

    public void onDisconnect() {
    }

    public void handle(Handshake packet) {
    }

    public void handle(Request packet) {
    }

    public void handle(Ping packet) {
    }

    public void handle(EncryptionResponse packet) {
    }

    public void handle(LoginStart packet) {
    }

    public void handle(ClientSettings packet) {
    }

    public void handle(PluginMessage packet) {
    }

    public void handle(PlayerPosition packet) {
    }

    public void handle(PlayerRotation packet) {
    }

    public void handle(PlayerPositionAndRotation packet) {
    }

    public void handle(TeleportConfirm packet) {
    }

    public void handle(KeepAliveIn packet) {
    }
}
