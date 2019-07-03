package framework;

/**
 * 协议接口：目前存在两个实现类
 */
public interface Protocol {

    /**
     * 启动
     *
     * @param url
     */
    void start(URL url);

    /**
     * 发送信息
     *
     * @param url
     * @param invocation
     * @return
     */
    String send(URL url, Invocation invocation);

}
