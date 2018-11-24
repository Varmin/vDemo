package com.varmin.javapro.design_mode.factory;

import com.varmin.javapro.design_mode.product.TvKangJiaProductImpl;
import com.varmin.javapro.design_mode.product.TvProduct;

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
