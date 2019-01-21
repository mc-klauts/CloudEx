package de.CloudEx.service.network.packet;

import de.CloudEx.service.services.network.CloudPacket;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

public abstract class CloudPacketDecoder<I> extends ChannelHandlerAdapter {

    protected abstract void decode(ChannelHandlerContext ctx, CloudPacket packet, List<I> out) throws Exception;
}
