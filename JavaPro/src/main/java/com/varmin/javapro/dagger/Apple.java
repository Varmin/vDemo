package com.varmin.javapro.dagger;

import javax.inject.Inject;

/**
 * Created by HuangYang
 * on 2018/11/6  15:08.
 * 文件描述：
 */
public class Apple {
    @Inject
    public Apple(){}

    @Override
    public String toString() {
        return "大苹果";
    }
}
