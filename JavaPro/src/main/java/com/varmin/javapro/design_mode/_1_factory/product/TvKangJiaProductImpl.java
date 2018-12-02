package com.varmin.javapro.design_mode._1_factory.product;

/**
 * Created by HuangYang
 * on 2018/11/24  16:39.
 * 文件描述：
 */
public class TvKangJiaProductImpl implements TvProduct {

    @Override
    public void play() {
        System.out.println("I am KanJia Tv");
    }
}
