package com.varmin.javapro.design_mode._2_proxy;

import com.varmin.javapro.base.BaseObj;
import com.varmin.javapro.design_mode._2_proxy.subject.UseMac;
import com.varmin.javapro.design_mode._2_proxy.subject.BuyMac;
import com.varmin.javapro.design_mode._2_proxy.subject.Buy;

/**
 * Created by HuangYang
 * on 2018/12/2  18:01.
 * 文件描述：
 */
public class ProxyMain implements BaseObj {
    @Override
    public void run() {
        /**
         * 静态代理：一对一
         */
        new BuyMacProxy().bug();

        /**
         * 动态代理：一对多，可以是不同类型的代理对象
         */
        BuyMac bm = new BuyMac();
        DynamicProxy bugProxy = new DynamicProxy();
        Buy buy = (Buy) bugProxy.getProxyInstance(bm);
        buy.bug();

        UseMac um = new UseMac();
        DynamicProxy useProxy = new DynamicProxy();
        UseMac useMac = (UseMac) useProxy.getProxyInstance(um);
        useMac.use();
    }
}
