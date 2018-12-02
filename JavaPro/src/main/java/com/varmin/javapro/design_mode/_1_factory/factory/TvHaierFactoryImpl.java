package com.varmin.javapro.design_mode._1_factory.factory;

import com.varmin.javapro.design_mode._1_factory.product.TvHaierProductImpl;
import com.varmin.javapro.design_mode._1_factory.product.TvProduct;

/**
 * Created by HuangYang
 * on 2018/11/24  16:41.
 * 文件描述：
 */
public class TvHaierFactoryImpl implements TvFactory {
    @Override
    public TvProduct getTv() {
        return new TvHaierProductImpl();
    }
}
