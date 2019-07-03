package register;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 本地注册模拟
 */
public class LocalRegister {
    private static ConcurrentMap<String, Class> map = new ConcurrentHashMap<>();

    public static void register(String interfaceName, Class implClass){
        map.put(interfaceName, implClass);
    }

    public static Class get(String interfaceName){
        return map.get(interfaceName);
    }
}
