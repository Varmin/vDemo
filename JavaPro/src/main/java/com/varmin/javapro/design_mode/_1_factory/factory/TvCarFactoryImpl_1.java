package com.varmin.javapro.design_mode._1_factory.factory;

/**
 * Created by HuangYang
 * on 2018/11/24  17:02.
 * 文件描述：
 */
public class TvCarFactoryImpl_1 implements AbstractFactory {

    @Override
    public TvFactory getTvFactory() {
        return new TvHaierFactoryImpl();
    }

    @Override
    public CarFactory getCarFactory() {
        return new CarFactoryAoDiImpl();
    }
}
