package com.varmin.vdemo.test.daggerTest;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HuangYang
 * on 2018/12/5  23:29.
 * 文件描述：
 */
@Module
public class BoyModule {
    @Provides
    Boy provideBoy(){
        return new Boy();
    }
}
