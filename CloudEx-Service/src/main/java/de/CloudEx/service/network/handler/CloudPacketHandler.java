package de.CloudEx.service.network.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public abstract class CloudPacketHandler<T> extends ChannelHandlerAdapter {

    protected abstract void messageReceived(ChannelHandlerContext ctx, T msg) throws Exception;
}
