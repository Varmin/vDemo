package com.varmin.vdemo.base;

import android.app.Application;

import com.varmin.multiplestatusview.MultipleHelper;

/**
 * Created by HuangYang
 * on 2018/12/13  14:52.
 * 文件描述：
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        MultipleHelper.getHelper();
    }
}
