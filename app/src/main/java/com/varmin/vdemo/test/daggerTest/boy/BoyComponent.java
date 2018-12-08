package com.varmin.vdemo.test.daggerTest.boy;

import dagger.Component;

/**
 * Created by HuangYang
 * on 2018/12/5  23:31.
 * 文件描述：
 */
@Component(modules = BoyModule.class)
public interface BoyComponent {
    //Boy boy();
    Girl providGirl();
}
