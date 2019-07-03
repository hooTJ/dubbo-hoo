package register;

import framework.URL;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 远程注册模拟
 */
public class RemoteRegister {

    private static ConcurrentMap<String, List<URL>> map = new ConcurrentHashMap<>();

    /**
     *注册
     *
     * @param interfaceName
     * @param url
     */
    public static void register(String interfaceName, URL url) {
        List<URL> urls;
        if (map.containsKey(interfaceName)) {
            urls = map.get(interfaceName);
            urls.add(url);
        } else {
            urls = new ArrayList<>();
            urls.add(url);
            map.put(interfaceName, urls);
        }
        saveFile();
    }

    /**
     * 把对象保存为本地文件
     */
    private static void saveFile() {
        System.out.println("存放文件：" + map);
        try (FileOutputStream fos = new FileOutputStream("/tmp.txt");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(map);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取本地文件
     */
    private static void getFile() {
        try (FileInputStream fis = new FileInputStream("/tmp.txt");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            map = (ConcurrentMap<String, List<URL>>) ois.readObject();
            System.out.println("读取文件：" + map);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 随机读取一个地址
     *
     * @param interfaceName
     * @return
     */
    public static URL random(String interfaceName) {
        List<URL> urls = map.get(interfaceName);
        if (urls == null || urls.size() < 1) {
            getFile();
            urls = map.get(interfaceName);
        }
        Random random = new Random();
        return urls.get(random.nextInt(urls.size()));
    }

}
