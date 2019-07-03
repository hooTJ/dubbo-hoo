package protocol.dubbo;

import framework.Invocation;
import framework.Protocol;
import framework.URL;

public class DubboProtocol implements Protocol{
    @Override
    public void start(URL url) {
        NettyServer nettyServer = new NettyServer();
        nettyServer.start(url.getHostname(), url.getPort());
    }

    @Override
    public String send(URL url, Invocation invocation) {
        NettyClient nettyClient= new NettyClient();
        return nettyClient.send(url.getHostname(), url.getPort(), invocation);
    }
}
