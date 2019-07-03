package consumer;

import framework.ProxyFactory;
import provider.api.HelloService;

public class Consumer {

    public static void main(String[] args) {
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        System.out.println(helloService.hello("张三"));
    }

}
