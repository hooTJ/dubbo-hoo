package framework;

import protocol.http.HttpClient;
import register.RemoteRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理工厂
 */
public class ProxyFactory {

    /**
     * 获取代理对象（JDK）
     *
     * @param interfaceClass
     * @param <T>
     * @return
     */
    public static <T> T getProxy(final Class<provider.api.HelloService> interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(),
                        method.getParameterTypes(), args);
                Protocol protocol = ProtocolFactory.getProtocol();
                URL url = RemoteRegister.random(interfaceClass.getName());
                String result = protocol.send(url, invocation);
                return result;
            }
        });
    }

}
