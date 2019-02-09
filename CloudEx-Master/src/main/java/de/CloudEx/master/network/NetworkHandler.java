package de.CloudEx.master.network;

import de.CloudEx.master.network.packets.AuthPacket;
import de.CloudEx.service.network.handler.CloudPacketHandler;
import de.CloudEx.service.services.logging.Logger;
import de.CloudEx.service.services.logging.level.INFO;
import de.CloudEx.service.services.network.CloudPacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

public class NetworkHandler extends CloudPacketHandler<CloudPacket> {

    private static Channel cloudChannel;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        new Logger(INFO.class, "A Wrapper connected!");
        new Logger(INFO.class, "Fetching AuthPacket...");
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, CloudPacket cloudPacket) throws Exception {

    }

    public static Channel getCloudChannel() {
        return cloudChannel;
    }
}
