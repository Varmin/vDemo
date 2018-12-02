package com.varmin.javapro.design_mode._2_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by HuangYang
 * on 2018/12/2  18:06.
 * 文件描述：
 */
public class BuyProxy implements InvocationHandler {

    private Object mObjectProxy;

    public Object getProxyInstance(Object objectProxy){
        this.mObjectProxy = objectProxy;

        /**
         * 参数1：指定产生代理对象的类加载器，需要将其指定为和目标对象同一个类加载器
         * 参数2：指定目标对象的实现接口
         * 即要给目标对象提供一组什么接口。若提供了一组接口给它，那么该代理对象就默认实现了该接口，这样就能调用这组接口中的方法
         * 参数3：指定InvocationHandler对象。即动态代理对象在调用方法时，会关联到哪个InvocationHandler对象
         */
        return Proxy.newProxyInstance(objectProxy.getClass().getClassLoader(),
                objectProxy.getClass().getInterfaces(),
                this);
    }

    /**
     * @param proxy 动态代理对象（即哪个动态代理对象调用了method（）
     * @param method 目标对象被调用的方法
     * @param args 指定被调用方法的参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代购开始~");
        Object result = method.invoke(mObjectProxy, args);
        System.out.println("代购完成，回国发货了。");
        return result;
    }
}
