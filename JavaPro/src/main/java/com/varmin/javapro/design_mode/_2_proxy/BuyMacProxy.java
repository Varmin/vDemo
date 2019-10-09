package com.varmin.javapro.design_mode._2_proxy;

import com.varmin.javapro.design_mode._2_proxy.subject.Buy;
import com.varmin.javapro.design_mode._2_proxy.subject.BuyMac;

/**
 * Created by HuangYang
 * on 2018/12/2  18:26.
 * 文件描述：静态代理
 */
public class BuyMacProxy implements Buy {
    @Override
    public void bug() {
        System.out.println("静态代理 start");
        BuyMac buyMac = new BuyMac();
        buyMac.bug();
        System.out.println("静态代理 end");
    }
}
