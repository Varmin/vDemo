package com.varmin.javapro.design_mode.factory;

import com.varmin.javapro.design_mode.product.Car;
import com.varmin.javapro.design_mode.product.CarHongQiImpl;

/**
 * Created by HuangYang
 * on 2018/11/24  17:00.
 * 文件描述：
 */
public class CarFactoryHongQiImpl implements CarFactory {
    @Override
    public Car getCar() {
        return new CarHongQiImpl();
    }
}
