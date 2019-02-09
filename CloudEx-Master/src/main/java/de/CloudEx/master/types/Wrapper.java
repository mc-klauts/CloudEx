package de.CloudEx.master.types;

public class Wrapper {

    private static String wrapperName;
    private static String ipAddress;

    public Wrapper(String wrapperName, String ipAddress) {
        this.wrapperName = wrapperName;
        this.ipAddress = ipAddress;
    }

    public String getWrapperName() {
        return wrapperName;
    }
    public String getIpAddress() {
        return ipAddress;
    }
}
