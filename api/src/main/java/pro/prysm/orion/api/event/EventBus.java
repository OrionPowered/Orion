package pro.prysm.orion.api.event;

import pro.prysm.orion.api.event.events.PacketEvent;
import pro.prysm.orion.api.protocol.Packet;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class EventBus {
    private final ConcurrentHashMap<Listener, List<Method>> listeners = new ConcurrentHashMap<>();

    public void subscribe(Listener listener) {
        listeners.put(listener, listener.getMethodsByPrio());
    }

    public void unSubscribe(Listener listener) {
        listeners.remove(listener);
    }

    public synchronized void post(PacketEvent event, Packet packet) {
        listeners.forEach((listener, methods) -> methods.forEach(method -> {
            try {
                Parameter[] parameters = method.getParameters();
                if (parameters[0].getType().equals(event.getClass()) && parameters[1].getType().equals(packet.getClass())) {
                    method.invoke(listener, event, packet);
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }));
    }

    public synchronized void post(Event event) {
        listeners.forEach((listener, methods) -> methods.forEach(method -> {
            try {
                Class<?> param = method.getParameters()[0].getType();
                if (param.equals(event.getClass())) {
                    method.invoke(listener, event);
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }));
    }
}
