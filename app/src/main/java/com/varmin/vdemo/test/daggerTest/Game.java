package com.varmin.vdemo.test.daggerTest;

import javax.inject.Inject;

/**
 * Created by HuangYang
 * on 2018/11/12  16:39.
 * 文件描述：
 */
public class Game {
    @Inject
    public Game(){}

    @Override
    public String toString() {
        return "游戏";
    }
}
