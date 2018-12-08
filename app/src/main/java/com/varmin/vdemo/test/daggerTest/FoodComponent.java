package com.varmin.vdemo.test.daggerTest;

import com.varmin.vdemo.test.daggerTest.boy.Boy;
import com.varmin.vdemo.test.daggerTest.boy.BoyComponent;

import dagger.Component;

/**
 * Created by HuangYang
 * on 2018/11/12  17:56.
 * 文件描述：
 */
@Component(modules = FoodModule.class, dependencies = BoyComponent.class)
public interface FoodComponent {
    //BaoMiHua getBaoMiHua();
    //ZaiNan2 privateUse();
    Boy boy();

}
