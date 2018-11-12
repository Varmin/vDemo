package com.varmin.vdemo.test.daggerTest;

import javax.inject.Inject;

/**
 * Created by HuangYang
 * on 2018/11/12  16:37.
 * 文件描述：
 */
public class ZaiNan {
    @Inject
    BaoZi baoZi;

    @Inject
    public ZaiNan(){}

    @Override
    public String toString() {
        return baoZi.toString();
    }
}
