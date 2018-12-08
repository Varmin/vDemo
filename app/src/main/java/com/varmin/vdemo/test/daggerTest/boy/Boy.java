package com.varmin.vdemo.test.daggerTest.boy;

import javax.inject.Inject;

/**
 * Created by HuangYang
 * on 2018/12/5  23:29.
 * 文件描述：
 */
public class Boy {
    @Inject
    public Boy(){}
    @Inject
    Girl girl;

    @Override
    public String toString() {
        return girl.toString();
    }
}
