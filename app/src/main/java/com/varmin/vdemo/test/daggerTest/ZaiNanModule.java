package com.varmin.vdemo.test.daggerTest;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HuangYang
 * on 2018/11/12  17:06.
 * 文件描述：
 */
@Module
public class ZaiNanModule {
    @Provides
    Girl providesGirl(){
        return new Girl();
    }
}
