package de.CloudEx.service.network.handler;

import de.CloudEx.service.core.Validator;
import de.CloudEx.service.network.request.RequestService;
import de.CloudEx.service.services.logging.Logger;
import de.CloudEx.service.services.logging.level.NETTY;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.HashMap;
import java.util.Map;

public class NetworkHandler extends SimpleChannelInboundHandler<String> {

    public static Map<String, Integer> wrapperChannels = new HashMap<>();

    public static Channel channel;
    public static ChannelGroup wrappers = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.setChannel(ctx.channel());
        wrappers.add(ctx.channel());
        new Logger(NETTY.class, "Eine Wrapper-Verbindung wurde aufgebaut! -> "+ctx.channel().remoteAddress());
        new Logger(NETTY.class, "Farge Wrapper-Token an... -> "+ctx.channel().remoteAddress());
        ctx.channel().writeAndFlush(RequestService.request_auth());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        new Logger(NETTY.class, "Eine Wrapper-Verbindung wurde geschlossen! -> "+channel.remoteAddress());
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
        String[] args = msg.split(" ");
        if(args[0].equalsIgnoreCase("response_auth")) {
            System.out.println(args[2]+ " " +args[1]);
            if(Validator.validateWrapper(args[2], args[1])) {
                new Logger(NETTY.class, "CloudEx hat einen Wrapper erfolgreich registriert -> "+args[2]);
            } else {
                wrappers.remove(ctx.channel());
                ctx.channel().close();
                new Logger(NETTY.class, "CloudEx hat einen Wrapper  -> "+args[2]);
            }
        }
    }

    public static void setChannel(Channel channel) {
        NetworkHandler.channel = channel;
    }

    public static ChannelGroup getChannel() {
        return wrappers;
    }

}
