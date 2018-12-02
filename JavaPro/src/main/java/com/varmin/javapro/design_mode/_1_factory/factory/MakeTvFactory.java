package com.varmin.javapro.design_mode._1_factory.factory;

import com.varmin.javapro.design_mode._1_factory.product.TvHaierProductImpl;
import com.varmin.javapro.design_mode._1_factory.product.TvKangJiaProductImpl;
import com.varmin.javapro.design_mode._1_factory.product.TvProduct;

/**
 * Created by HuangYang
 * on 2018/11/24  16:44.
 * 文件描述：简单工厂模式
 */
public class MakeTvFactory {
    public static TvProduct getTvProduct(String type){
        TvProduct tvProduct = null;
        switch (type) {
            case "Haier":
                tvProduct = new TvHaierProductImpl();
                break;
            case "KangJia":
                tvProduct = new TvKangJiaProductImpl();
                break;
        }

        return tvProduct;
    }
}
