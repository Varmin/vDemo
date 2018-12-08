package com.varmin.javapro.design_mode._3_build;

import com.varmin.javapro.base.BaseObj;

/**
 * Created by HuangYang
 * on 2018/12/8  19:06.
 * 文件描述：
 */
public class BuilderMain implements BaseObj {
    @Override
    public void run() {
        new RealBuilder().build()
                .addCPU("添加CPU")
                .addMemory("添加内存")
                .addDisk("添加硬盘")
                .creat().start();

        RealBuilder.builder()
                .creat().start();
    }
}
