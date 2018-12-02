package com.varmin.javapro.design_mode._1_factory.factory;

/**
 * Created by HuangYang
 * on 2018/11/24  16:51.
 * 文件描述：
 */
public interface AbstractFactory {
    TvFactory getTvFactory();
    CarFactory getCarFactory();
}
