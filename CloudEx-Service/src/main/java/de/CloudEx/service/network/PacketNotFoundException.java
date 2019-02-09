package de.CloudEx.service.network;

public class PacketNotFoundException extends RuntimeException {

    /** Sending the default message of the Error **/
    public PacketNotFoundException() { super("A Packet could not be found!"); }

    /** Sending the PacketNotFound error with a specific message **/
    public PacketNotFoundException(String s) { super(s); }
}
