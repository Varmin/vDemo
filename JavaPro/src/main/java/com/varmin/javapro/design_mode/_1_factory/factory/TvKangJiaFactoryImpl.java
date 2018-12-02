package com.varmin.javapro.design_mode._1_factory.factory;

import com.varmin.javapro.design_mode._1_factory.product.TvKangJiaProductImpl;
import com.varmin.javapro.design_mode._1_factory.product.TvProduct;

/**
 * Created by HuangYang
 * on 2018/11/24  16:42.
 * 文件描述：
 */
public class TvKangJiaFactoryImpl implements TvFactory {
    @Override
    public TvProduct getTv() {
        return new TvKangJiaProductImpl();
    }
}
