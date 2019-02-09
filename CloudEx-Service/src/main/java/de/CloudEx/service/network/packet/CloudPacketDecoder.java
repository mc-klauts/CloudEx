package de.CloudEx.service.network.packet;

import de.CloudEx.service.services.network.CloudPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

public abstract class CloudPacketDecoder extends ChannelHandlerAdapter {

    protected abstract void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception;
}
