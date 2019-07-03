package protocol.dubbo;

import framework.Invocation;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import register.LocalRegister;

import java.lang.reflect.Method;

/**
 * netty 处理器
 */
public class NettyServerHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("objectDecoder", new ObjectDecoder(1024 * 1024, ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
        pipeline.addLast("objectEncoder", new ObjectEncoder());
        pipeline.addLast("hooHandler", new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                Invocation invocation = (Invocation) msg;
                Class implClass = LocalRegister.get(invocation.getInterfaceName());
                Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamsType());
                Object invoke = method.invoke(implClass.newInstance(), invocation.getParams());
                System.out.println("netty 执行的结果为： "+ invoke.toString());
                // 注意，这个必须加上：addListener(ChannelFutureListener.CLOSE);
                ctx.writeAndFlush(invoke.toString()).addListener(ChannelFutureListener.CLOSE);
            }
        });
    }
}
