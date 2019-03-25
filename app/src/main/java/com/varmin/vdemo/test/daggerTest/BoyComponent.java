package com.varmin.vdemo.test.daggerTest;

import dagger.Subcomponent;

/**
 * Created by HuangYang
 * on 2018/12/5  23:31.
 * 文件描述：
 */
@Subcomponent(modules = BoyModule.class)
public interface BoyComponent {
    //Boy getBoy();
    ZaiNan getZaiNa();
}
