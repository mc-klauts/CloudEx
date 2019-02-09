package de.CloudEx.wrapper.network.handler;

import de.CloudEx.service.CloudService;
import de.CloudEx.service.network.PacketNotFoundException;
import de.CloudEx.service.network.packet.CloudPacketDecoder;
import de.CloudEx.service.services.network.CloudPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

public class PacketDecoder extends CloudPacketDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
        int id = byteBuf.readInt();
        Class<? extends CloudPacket> packetClass = CloudService.getInstance().IN_PACKETS.get(id);
        if(packetClass == null)
            throw new PacketNotFoundException("Couldn't find packet!\npacket: "+packetClass.getSimpleName()+"\nid: "+id);

        CloudPacket cloudPacket = packetClass.newInstance();
        cloudPacket.read(byteBuf);
    }
}
