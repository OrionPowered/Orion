package pro.prysm.orion.server.protocol.bidirectional;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pro.prysm.orion.api.entity.player.PlayerAbility;
import pro.prysm.orion.api.util.CollectorUtil;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.incoming.IncomingPacket;
import pro.prysm.orion.common.protocol.outgoing.OutgoingPacket;

import java.util.Arrays;


public class PlayerAbilities {

    @Getter
    public static class Incoming extends IncomingPacket {
        private PlayerAbility ability;

        @Override
        public void read(PacketByteBuf buf) {
            byte b = buf.readByte();
            ability = Arrays.stream(PlayerAbility.values()).filter(a -> a.getServerBoundId() == b).collect(CollectorUtil.toSingleton());
        }
    }

    @Setter
    public static class Outgoing extends OutgoingPacket {
        private boolean invulnerable;   // bit 0x01
        private boolean flying;         // bit 0x02
        private boolean allowFlight;    // bit 0x04
        private boolean instantBreak;   // bit 0x08
        private float flySpeed;
        private float fieldOfViewModifier;

        public Outgoing(boolean invulnerable, boolean flying, boolean allowFlight, boolean instantBreak, float flySpeed, float fieldOfViewModifier) {
            super(0x32);
            this.invulnerable = invulnerable;
            this.flying = flying;
            this.allowFlight = allowFlight;
            this.instantBreak = instantBreak;
            this.flySpeed = flySpeed;
            this.fieldOfViewModifier = fieldOfViewModifier;
        }

        @Override
        public void write(PacketByteBuf buf) {
            byte b = 0x0;
            if (invulnerable) b |= 0x01;
            if (flying) b |= 0x01 << 1;
            if (allowFlight) b |= 0x01 << 2;
            if (instantBreak) b |= 0x01 << 3;
            buf.writeByte(b);
            buf.writeFloat(flySpeed);
            buf.writeFloat(fieldOfViewModifier);
        }
    }
}
