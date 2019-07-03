package framework;

import java.io.Serializable;

/**
 * 请求参数封装成对象
 */
// invocation 英文单词是“调用”的意思
public class Invocation implements Serializable {
    /**
     * 接口全类名
     */
    private String interfaceName;
    /**
     * 调用方法名称
     */
    private String methodName;
    /**
     * 方法参数类型
     */
    private Class[] paramsType;
    /**
     * 方法参数
     */
    private Object[] params;

    public Invocation(String interfaceName, String methodName, Class[] paramsType, Object[] params) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.paramsType = paramsType;
        this.params = params;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParamsType() {
        return paramsType;
    }

    public void setParamsType(Class[] paramsType) {
        this.paramsType = paramsType;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
