package framework;

import protocol.dubbo.DubboProtocol;
import protocol.http.HttpProtocol;

/**
 * 协议工厂类
 */
public class ProtocolFactory {

    public static Protocol getProtocol() {
        Protocol protocol;
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
        return protocol;
    }

}
