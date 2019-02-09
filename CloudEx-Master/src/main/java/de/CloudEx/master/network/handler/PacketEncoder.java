package de.CloudEx.master.network.handler;

import de.CloudEx.service.CloudService;
import de.CloudEx.service.network.PacketNotFoundException;
import de.CloudEx.service.network.packet.CloudPacketEncoder;
import de.CloudEx.service.services.network.CloudPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class PacketEncoder extends CloudPacketEncoder<CloudPacket> {

    @Override
    protected void encode(ChannelHandlerContext ctx, CloudPacket packet, ByteBuf out) throws Exception {
        int id = CloudService.getInstance().OUT_PACKETS.indexOf(packet.getClass());

        if(id == -1)
            throw new PacketNotFoundException("Cloudn't find packet!\npacket: "+packet.getClass().getSimpleName()+"\nid: "+id);

        out.writeInt(id);
        packet.write(out);
    }
}
