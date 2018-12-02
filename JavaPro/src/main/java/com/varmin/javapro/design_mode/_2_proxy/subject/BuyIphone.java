package com.varmin.javapro.design_mode._2_proxy.subject;

/**
 * Created by HuangYang
 * on 2018/12/2  18:04.
 * 文件描述：
 */
public class BuyIphone implements Buy {
    @Override
    public void bug() {
        System.out.println("Buy iphone!");
    }
}
