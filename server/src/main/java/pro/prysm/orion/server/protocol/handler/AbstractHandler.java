package pro.prysm.orion.server.protocol.handler;

import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.common.net.Connection;
import pro.prysm.orion.common.protocol.Handler;
import pro.prysm.orion.common.protocol.incoming.handshaking.Handshake;
import pro.prysm.orion.common.protocol.incoming.login.EncryptionResponse;
import pro.prysm.orion.common.protocol.incoming.login.LoginStart;
import pro.prysm.orion.common.protocol.incoming.play.*;
import pro.prysm.orion.common.protocol.incoming.status.Ping;
import pro.prysm.orion.common.protocol.incoming.status.Request;
import pro.prysm.orion.server.protocol.handler.play.PlayHandler;

public abstract class AbstractHandler implements Handler {
    protected final Connection connection;

    public AbstractHandler(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Handler getForState(PacketState state) {
        return switch (state) {
            case STATUS -> new StatusHandler(connection);
            case LOGIN -> new LoginHandler(connection);
            case PLAY -> new PlayHandler(((LoginHandler) this).getPlayer());
            default -> throw new IllegalStateException("Unexpected state: " + state);
        };
    }

    @Override
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

    public void handle(PluginMessageIn packet) {
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

    public void handle(ChatMessageIn packet) {
    }

    public void handle(Pong packet) {
    }
}
