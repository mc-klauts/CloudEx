package de.CloudEx.service.services.network;

public interface CloudPacket {

    /** Reading data thou packets **/
    void read(CloudPacketSerializer cloudPacketSerializer);

    /** Writing data thou packets **/
    void write(CloudPacketSerializer cloudPacketSerializer);
}
