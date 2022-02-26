package pro.prysm.orion.common;

import lombok.Getter;
import pro.prysm.orion.common.net.Connection;
import pro.prysm.orion.common.protocol.Protocol;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public abstract class AbstractServer {
    private final Protocol protocol = new Protocol();
    private final Map<Integer, Connection> connections = new ConcurrentHashMap<>();

    public abstract void onReload();
}
