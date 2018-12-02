package com.varmin.javapro.design_mode._2_proxy;

import com.varmin.javapro.base.BaseObj;
import com.varmin.javapro.design_mode._2_proxy.subject.BuyIphone;
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
        new BuyMacProxy().bug();

        BuyProxy buyProxy1 = new BuyProxy();
        BuyMac bm = new BuyMac();
        Buy buyMac = (Buy) buyProxy1.getProxyInstance(bm);
        buyMac.bug();

        BuyProxy buyProxy2 = new BuyProxy();
        BuyIphone bi = new BuyIphone();
        Buy buyIphone = (Buy) buyProxy2.getProxyInstance(bi);
        buyIphone.bug();
    }
}
