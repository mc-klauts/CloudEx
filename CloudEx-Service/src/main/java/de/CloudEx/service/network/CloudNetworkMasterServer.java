package de.CloudEx.service.network;

import de.CloudEx.service.core.CloudNetworkMasterCommandSystem;
import de.CloudEx.service.network.handler.CloudPacketHandler;
import de.CloudEx.service.network.packet.CloudPacketRegistry;
import de.CloudEx.service.services.logging.Logger;
import de.CloudEx.service.services.logging.level.ERROR;
import de.CloudEx.service.services.logging.level.INFO;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class CloudNetworkMasterServer {

    private static final boolean EPOLL = Epoll.isAvailable();
    private static EventLoopGroup eventLoopGroup;
    private static ServerBootstrap serverBootstrap;
    public static boolean isReady = false;

    private static int port;
    private static String ip;

    public CloudNetworkMasterServer(CloudSocketAddress cloudSocketAddress, CloudPacketRegistry cloudPacketRegistry, final CloudPacketHandler cloudPacketHandler) {
        try {
            this.port = cloudSocketAddress.getPort();
            this.ip = cloudSocketAddress.getAddress();

            this.eventLoopGroup = EPOLL ? new EpollEventLoopGroup() : new NioEventLoopGroup();

            new ServerBootstrap()
                    .group(this.eventLoopGroup)
                    .channel(EPOLL ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(cloudPacketHandler);
                            isReady = true;
                        }
                    }).bind(this.ip, this.port).sync().channel();
            CloudNetworkMasterCommandSystem.getInstance().launch();

        } catch (Exception e) {
            new Logger(ERROR.class, "CloudNetworkMasterServer: "+e);

        } finally {
            this.eventLoopGroup.shutdownGracefully();
        }
    }

    public void tryBind() {
        if(!this.isReady) {
            try {
                new Logger(INFO.class, "Netty-Server bound to: " + this.ip + ":" + this.port);
            } catch (Exception e) {
                new Logger(ERROR.class, "CloudNetworkMasterServer: "+e);
            }
        }
    }
}
