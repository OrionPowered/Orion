package pro.prysm.orion.common.command.commands;

import pro.prysm.orion.server.Orion;
import pro.prysm.orion.common.command.Command;
import pro.prysm.orion.server.net.pipeline.ChannelHandler;

/**
 * @author 254n_m
 * @since 12/18/21 / 8:57 PM
 * This file was created as a part of Orion
 */
public class SendPacketCommand extends Command {
    private final ChannelHandler channelHandler;

    public SendPacketCommand(ChannelHandler channelHandler) {
        super("sendpacket", "Sends a packet");
        this.channelHandler = channelHandler;
    }

    @Override
    public void execute(String[] args, String name) {
        if (args.length > 2) {

        } else Orion.getLogger().warn("sendpacket <PacketID> <PacketParameters <DataType::Value>>");
    }
}
