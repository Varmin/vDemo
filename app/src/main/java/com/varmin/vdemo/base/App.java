package com.varmin.vdemo.base;

import android.app.Application;

import com.varmin.multiplestatusview.MultipleHelper;

/**
 * Created by HuangYang
 * on 2018/12/13  14:52.
 * 文件描述：
 */
public class App extends Application {
    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mInstance = this;
        init();
    }
    public static App getApplication(){return mInstance;}

    private void init() {
        MultipleHelper.getHelper();
    }
}
