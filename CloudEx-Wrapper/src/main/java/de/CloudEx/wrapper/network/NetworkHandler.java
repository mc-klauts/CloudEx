package de.CloudEx.wrapper.network;

import de.CloudEx.service.network.handler.CloudPacketHandler;
import de.CloudEx.service.services.logging.Logger;
import de.CloudEx.service.services.logging.level.INFO;
import de.CloudEx.service.services.network.CloudPacket;
import de.CloudEx.wrapper.network.packets.AuthPacket;
import io.netty.channel.ChannelHandlerContext;

public class NetworkHandler extends CloudPacketHandler<CloudPacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        new Logger(INFO.class, "Connected to Master!");
        new Logger(INFO.class, "Sending AuthPacket...");
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, CloudPacket cloudPacket) throws Exception {
        if(cloudPacket instanceof AuthPacket) {

        }
    }
}
