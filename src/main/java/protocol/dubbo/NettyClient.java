package protocol.dubbo;

import framework.Invocation;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {

    public String send(String hostname, Integer port, Invocation invocation) {
        EventLoopGroup group = new NioEventLoopGroup();
        NettyClientHandler handler = new NettyClientHandler();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class).handler(handler);
            ChannelFuture channelFuture = bootstrap.connect(hostname, port).sync();
            Channel channel = channelFuture.channel();
            channel.writeAndFlush(invocation).sync();
            channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
        String result = handler.responseResult().toString();
        return result;
    }

}
