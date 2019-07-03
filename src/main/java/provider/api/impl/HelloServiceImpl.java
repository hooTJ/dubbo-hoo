package provider.api.impl;

import provider.api.HelloService;

public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "hi " + name;
    }

}
