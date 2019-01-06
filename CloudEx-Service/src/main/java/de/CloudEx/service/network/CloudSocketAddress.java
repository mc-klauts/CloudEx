package de.CloudEx.service.network;

public class CloudSocketAddress {

    private static String addr = "";
    private static int port = 0;

    /** Constructor for getting and creating a new SocketAddress for the CloudNetwork **/
    public CloudSocketAddress(String addr, int port) {
        this.addr = addr;
        this.port = port;
    }

    public int getPort() {
        return this.port;
    }

    public String getAddress() {
        return this.addr;
    }
}
