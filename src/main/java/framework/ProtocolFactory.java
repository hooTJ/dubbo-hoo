package framework;

import protocol.dubbo.DubboProtocol;
import protocol.http.HttpProtocol;

import java.util.ServiceLoader;

/**
 * 协议工厂类
 */
public class ProtocolFactory {

    public static Protocol getProtocol() {
        // SPI
        ServiceLoader<Protocol> serviceLoader = ServiceLoader.load(Protocol.class);
        Protocol protocol = serviceLoader.iterator().next();
        if (protocol==null){
            // 系统属性
            switch (System.getProperty("protocolName","dubbo")) {
                case "http":
                    protocol = new HttpProtocol();
                    break;
                case "dubbo":
                    protocol = new DubboProtocol();
                    break;
                default:
                    protocol = new DubboProtocol();
                    break;
            }
        }
        return protocol;
    }

}
