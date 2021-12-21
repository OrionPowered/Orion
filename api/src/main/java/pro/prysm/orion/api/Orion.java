package pro.prysm.orion.api;

import pro.prysm.orion.api.event.EventBus;

/**
 * @author 254n_m
 * @since 12/21/21 / 1:21 AM
 * This file was created as a part of Orion
 */
public interface Orion {
     EventBus EVENT_BUS = new EventBus();
      static EventBus getEventBus() {
          return EVENT_BUS;
      }
}
