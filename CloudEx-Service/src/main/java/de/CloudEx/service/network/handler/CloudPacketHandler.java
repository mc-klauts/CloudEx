package de.CloudEx.service.network.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public abstract class CloudPacketHandler<CloudPacket> extends ChannelHandlerAdapter {

    protected abstract void messageReceived(ChannelHandlerContext ctx, CloudPacket msg) throws Exception;
}
