package com.varmin.vdemo.test.daggerTest;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by HuangYang
 * on 2018/11/12  16:37.
 * 文件描述：
 */
public class ZaiNan {
    private static final String TAG = "ZaiNan";
    //inject
    @Inject
    BaoZi baoZi;
    //provides
    @Inject
    Girl girl;
    //依赖的Component
    @Inject
    BaoMiHua baoMiHua;
    //继承的Component
//    @Inject
//    Boy boy;

    @Inject
    public ZaiNan(){}

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(baoZi.toString()).append("\n")
                .append(girl.toString()).append("\n")
                .append(baoMiHua.toString()).append("\n")
//                .append(boy.toString())
        ;
        Log.d(TAG, "toString: "+str.toString());
        return str.toString();
    }
}
