package pro.prysm.orion.common.event;

import pro.prysm.orion.api.event.Event;
import pro.prysm.orion.api.event.Listener;
import pro.prysm.orion.common.OrionExceptionHandler;
import pro.prysm.orion.common.event.events.PacketEvent;
import pro.prysm.orion.common.protocol.Packet;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class EventBus implements pro.prysm.orion.api.event.EventBus {
    private final ConcurrentHashMap<Listener, List<Method>> listeners = new ConcurrentHashMap<>();

    @Override
    public void subscribe(Listener listener) {
        listeners.put(listener, listener.getMethodsByPrio());
    }

    @Override
    public void unSubscribe(Listener listener) {
        listeners.remove(listener);
    }

    @Override
    public synchronized void post(Event event) {
        listeners.forEach((listener, methods) -> methods.forEach(method -> {
            try {
                Class<?> param = method.getParameters()[0].getType();
                if (param.equals(event.getClass())) {
                    method.invoke(listener, event);
                }
            } catch (Throwable t) {
                OrionExceptionHandler.error(t);
            }
        }));
    }

    public synchronized void post(PacketEvent event, Packet packet) {
        listeners.forEach((listener, methods) -> methods.forEach(method -> {
            try {
                Parameter[] parameters = method.getParameters();
                if (parameters[0].getType().equals(event.getClass()) && parameters[1].getType().equals(packet.getClass())) {
                    method.invoke(listener, event, packet);
                }
            } catch (Throwable t) {
                OrionExceptionHandler.error(t);
            }
        }));
    }
}
