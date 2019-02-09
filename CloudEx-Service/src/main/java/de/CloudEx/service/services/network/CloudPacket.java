package de.CloudEx.service.services.network;

import io.netty.buffer.ByteBuf;

public interface CloudPacket {

    /** Reading data thou packets **/
    void read(ByteBuf cloudPacketSerializer);

    /** Writing data thou packets **/
    void write(ByteBuf cloudPacketSerializer);
}
