package com.varmin.vdemo.test.daggerTest;

import javax.inject.Inject;

/**
 * Created by HuangYang
 * on 2018/11/12  16:37.
 * 文件描述：
 */
public class ZaiNan {
    @Inject
    Game game;

    @Inject
    BaoZi baoZi;

    /*@Inject
    BaoMiHua baoMiHua;*/

    @Inject
    public ZaiNan(){}

    @Override
    public String toString() {
        String content = game.toString()
                +"-"+baoZi.toString()
                //+"-"+baoMiHua.toString()
                ;
        return content;
    }
}
