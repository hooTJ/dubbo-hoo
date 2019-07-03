package provider.api.impl;

import provider.api.HelloService;

/**
 * 测试接口的实现
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "hi " + name;
    }

    @Override
    public int add(int a, int b) {
        return a + b;
    }

}
