package consumer;

import framework.ProxyFactory;
import provider.api.HelloService;

/**
 * 服务消费者启动类
 */
public class Consumer {

    public static void main(String[] args) {
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        System.out.println(helloService.hello("张三"));
    }

}
