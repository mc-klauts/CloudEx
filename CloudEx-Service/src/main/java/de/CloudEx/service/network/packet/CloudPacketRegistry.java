package de.CloudEx.service.network.packet;

import de.CloudEx.service.CloudService;
import de.CloudEx.service.services.network.CloudPacket;
import de.CloudEx.service.services.network.CloudPacketDirection;

public class CloudPacketRegistry {

    private static CloudPacketDecoder cloudPacketDecoder;
    private static CloudPacketEncoder cloudPacketEncoder;

    public void addPacket(CloudPacketDirection cloudPacketDirection, CloudPacket cloudPacket) {
        try {
            if (cloudPacketDirection.equals(CloudPacketDirection.IN)) {
                CloudService.getInstance().IN_PACKETS.add(cloudPacket.getClass());

            } else if (cloudPacketDirection.equals(CloudPacketDirection.OUT)) {
                CloudService.getInstance().OUT_PACKETS.add(cloudPacket.getClass());

            } else if (cloudPacketDirection.equals(CloudPacketDirection.BOTH)) {
                CloudService.getInstance().IN_PACKETS.add(cloudPacket.getClass());
                CloudService.getInstance().OUT_PACKETS.add(cloudPacket.getClass());

            } else {
                System.out.println("CloudPacketRegistry: CloudPacketDirection error!");
            }
        } catch (Exception e) {
            System.out.println("CloudPacketRegistry: "+e);
        }
    }

    public void removePacket(CloudPacketDirection cloudPacketDirection, CloudPacket cloudPacket) {
        try {
            if (cloudPacketDirection.equals(CloudPacketDirection.IN)) {
                CloudService.getInstance().IN_PACKETS.remove(cloudPacket.getClass());

            } else if (cloudPacketDirection.equals(CloudPacketDirection.OUT)) {
                CloudService.getInstance().OUT_PACKETS.remove(cloudPacket.getClass());

            } else if (cloudPacketDirection.equals(CloudPacketDirection.BOTH)) {
                CloudService.getInstance().IN_PACKETS.remove(cloudPacket.getClass());
                CloudService.getInstance().OUT_PACKETS.remove(cloudPacket.getClass());

            } else {
                System.out.println("CloudPacketRegistry: CloudPacketDirection error!");
            }
        } catch (Exception e) {
            System.out.println("CloudPacketRegistry: "+e);
        }
    }

    public void setCloudPacketDecoder(CloudPacketDecoder cloudPacketDecoder) {
        CloudPacketRegistry.cloudPacketDecoder = cloudPacketDecoder;
    }

    public void setCloudPacketEncoder(CloudPacketEncoder cloudPacketEncoder) {
        CloudPacketRegistry.cloudPacketEncoder = cloudPacketEncoder;
    }

    public CloudPacketDecoder getCloudPacketDecoder() {
        return cloudPacketDecoder;
    }

    public CloudPacketEncoder getCloudPacketEncoder() {
        return cloudPacketEncoder;
    }
}
