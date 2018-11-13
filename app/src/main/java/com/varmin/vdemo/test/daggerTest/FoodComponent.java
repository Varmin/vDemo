package com.varmin.vdemo.test.daggerTest;

import dagger.Component;

/**
 * Created by HuangYang
 * on 2018/11/12  17:56.
 * 文件描述：
 */
@Component(modules = FoodModule.class)
public interface FoodComponent {
//    BaoZi baozi();
    BaoMiHua getBaoMiHua();
    /**
     * 1
     * dev
     *
     */

    /**
     * dev2
     */
}
