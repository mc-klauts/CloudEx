package de.CloudEx.service.network;

import de.CloudEx.service.network.handler.CloudPacketHandler;
import de.CloudEx.service.network.packet.CloudPacketRegistry;
import de.CloudEx.service.services.logging.Logger;
import de.CloudEx.service.services.logging.level.ERROR;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.epoll.EpollSocketChannelConfig;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class CloudNetworkWrapperServer {

    private static final boolean EPOLL = Epoll.isAvailable();
    private static EventLoopGroup eventLoopGroup;
    public static boolean isReady = false;

    private static int port;
    private static String ip;

    public CloudNetworkWrapperServer(CloudSocketAddress cloudSocketAddress, CloudPacketRegistry cloudPacketRegistry, final CloudPacketHandler cloudPacketHandler) {
        try {
            this.port = cloudSocketAddress.getPort();
            this.ip = cloudSocketAddress.getAddress();

            this.eventLoopGroup = EPOLL ? new EpollEventLoopGroup() : new NioEventLoopGroup();

            Channel channel = new Bootstrap()
                    .group(this.eventLoopGroup)
                    .channel(EPOLL ? EpollSocketChannel.class : NioSocketChannel.class)
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            channel.pipeline().addLast(cloudPacketHandler);
                            isReady = true;
                        }
                    }).bind(this.ip, this.port).sync().channel();

        } catch(Exception e) {
            new Logger(ERROR.class, "CloudNetworkWrapperServer: "+e);
        }
    }
}
