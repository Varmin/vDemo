package com.varmin.javapro.design_mode._1_factory;

import com.varmin.javapro.base.BaseObj;
import com.varmin.javapro.design_mode._1_factory.factory.TvCarFactoryImpl_1;
import com.varmin.javapro.design_mode._1_factory.factory.TvCarFactoryImpl_2;
import com.varmin.javapro.design_mode._1_factory.factory.TvHaierFactoryImpl;
import com.varmin.javapro.design_mode._1_factory.factory.TvKangJiaFactoryImpl;
import com.varmin.javapro.design_mode._1_factory.factory.MakeTvFactory;

/**
 * Created by HuangYang
 * on 2018/11/24  16:43.
 * 文件描述：
 */
public class FactoryTestMain implements BaseObj {
    @Override
    public void run() {
        System.out.println("简单工厂模式");
        MakeTvFactory.getTvProduct("Haier").play();
        MakeTvFactory.getTvProduct("KangJia").play();

        System.out.println("工厂模式");
        TvHaierFactoryImpl haierFac = new TvHaierFactoryImpl();
        haierFac.getTv().play();

        TvKangJiaFactoryImpl kangJiaFac = new TvKangJiaFactoryImpl();
        kangJiaFac.getTv().play();


        System.out.println("抽象工厂模式");
        TvCarFactoryImpl_1 factory1 = new TvCarFactoryImpl_1();
        factory1.getTvFactory().getTv().play();
        factory1.getCarFactory().getCar().run();

        TvCarFactoryImpl_2 factory2 = new TvCarFactoryImpl_2();
        factory2.getTvFactory().getTv().play();
        factory2.getCarFactory().getCar().run();
    }

}
