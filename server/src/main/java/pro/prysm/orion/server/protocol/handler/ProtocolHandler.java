package pro.prysm.orion.server.protocol.handler;

import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.protocol.Protocol;
import pro.prysm.orion.server.protocol.incoming.login.EncryptionResponse;
import pro.prysm.orion.server.protocol.incoming.login.LoginStart;
import pro.prysm.orion.server.protocol.incoming.status.Handshake;
import pro.prysm.orion.server.protocol.incoming.status.Ping;

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
}
