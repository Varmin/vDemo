package com.varmin.vdemo.test.daggerTest;

import com.varmin.vdemo.test.TestActivity;

import dagger.Component;

/**
 * Created by HuangYang
 * on 2018/11/12  16:42.
 * 文件描述：
 */
@Component(modules = ZaiNanModule.class)
public interface ZaiNanComponent {
    ZaiNan waimai();
    void inject(TestActivity testActivity);
}
