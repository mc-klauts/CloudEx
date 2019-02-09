package de.CloudEx.master.types;

public class Master {

    private String host;
    private int port;

    public Master(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
