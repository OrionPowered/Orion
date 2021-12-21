package pro.prysm.test.listener;

import pro.prysm.orion.api.event.EventHandler;
import pro.prysm.orion.api.event.Listener;
import pro.prysm.orion.api.event.events.IncomingPacketEvent;
import pro.prysm.orion.api.protocol.incoming.IncomingPacket;
import pro.prysm.orion.api.protocol.incoming.login.LoginStart;


/**
 * @author 254n_m
 * @since 12/21/21 / 1:29 AM
 * This file was created as a part of Orion
 */
public class TestPacketClass implements Listener {
    @EventHandler
    public void onLoginStart(IncomingPacketEvent event, LoginStart packet) {
        System.out.println(packet.getUsername());
    }
}
