package com.varmin.javapro.design_mode.factory;

/**
 * Created by HuangYang
 * on 2018/11/24  17:02.
 * 文件描述：
 */
public class TvCarFactoryImpl_2 implements AbstractFactory {

    @Override
    public TvFactory getTvFactory() {
        return new TvKangJiaFactoryImpl();
    }

    @Override
    public CarFactory getCarFactory() {
        return new CarFactoryHongQiImpl();
    }
}
