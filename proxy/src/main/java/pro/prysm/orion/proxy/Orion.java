package pro.prysm.orion.proxy;

import pro.prysm.orion.common.AbstractOrion;

public class Orion extends AbstractOrion {
    private static Proxy proxy;
    public static void main(String[] args) {
        proxy = new Proxy();
    }

    public static Proxy getProxy() {
        return proxy;
    }
}
