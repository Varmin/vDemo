package com.varmin.javapro.design_mode._2_proxy.subject;

/**
 * Created by HuangYang
 * on 2018/12/2  18:03.
 * 文件描述：
 */
public class BuyMac implements Buy {
    @Override
    public void bug() {
        System.out.println("Buy Mac!");
    }
}
