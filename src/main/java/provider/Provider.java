package provider;

import framework.Protocol;
import framework.ProtocolFactory;
import framework.URL;
import protocol.http.HttpServer;
import provider.api.HelloService;
import provider.api.impl.HelloServiceImpl;
import register.LocalRegister;
import register.RemoteRegister;

/**
 * 服务提供者启动类
 */
public class Provider {

    public static void main(String[] args) {
        // 本地注册
        // 服务名：实现类
        LocalRegister.register(HelloService.class.getName(), HelloServiceImpl.class);

        // 远程注册
        // 服务名：List<URL>
        URL url = new URL("localhost", 8080);
        RemoteRegister.register(HelloService.class.getName(), url);

        // 启动服务
        Protocol protocol = ProtocolFactory.getProtocol();
        protocol.start(url);
    }

}
