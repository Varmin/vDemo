package com.varmin.javapro.dagger;

import com.varmin.javapro.base.BaseObj;

import javax.inject.Inject;

/**
 * Created by HuangYang
 * on 2018/11/6  15:13.
 * 文件描述：
 */
public class MainRun implements BaseObj {
    @Inject
    public MainRun(){

    }
    @Inject
    Apple apple;


    @Override
    public void run() {
        System.out.println(apple.toString()+"\n");
    }
}
