package com.varmin.javapro.design_mode.factory;

import com.varmin.javapro.design_mode.product.Car;
import com.varmin.javapro.design_mode.product.CarAoDiImpl;

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
