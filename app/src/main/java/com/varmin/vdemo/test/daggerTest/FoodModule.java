package com.varmin.vdemo.test.daggerTest;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HuangYang
 * on 2018/11/12  17:54.
 * 文件描述：
 */
@Module
public class FoodModule {
    @Provides
    BaoMiHua providesBaoMiHua(){
        return new BaoMiHua();
    }
}
