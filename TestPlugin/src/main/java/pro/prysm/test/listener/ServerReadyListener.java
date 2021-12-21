package pro.prysm.test.listener;

import pro.prysm.orion.api.event.EventHandler;
import pro.prysm.orion.api.event.Listener;
import pro.prysm.orion.api.event.events.ServerReadyEvent;

/**
 * @author 254n_m
 * @since 12/21/21 / 1:20 AM
 * This file was created as a part of Orion
 */
public class ServerReadyListener implements Listener {
    @EventHandler
    public void onServerReady(ServerReadyEvent event) {
        System.out.println("This is from a plugin on server ready");
    }
}
