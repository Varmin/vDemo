package com.varmin.javapro.design_mode._1_factory.factory;

import com.varmin.javapro.design_mode._1_factory.product.Car;
import com.varmin.javapro.design_mode._1_factory.product.CarAoDiImpl;

/**
 * Created by HuangYang
 * on 2018/11/24  16:59.
 * 文件描述：
 */
public class CarFactoryAoDiImpl implements CarFactory {
    @Override
    public Car getCar() {
        return new CarAoDiImpl();
    }
}
