package com.varmin.javapro.design_mode._3_build;

/**
 * Created by HuangYang
 * on 2018/12/8  18:53.
 * 文件描述：
 */
public interface Builder {
    Builder build();
    Builder addCPU(String cpu);
    Builder addMemory(String memory);
    Builder addDisk(String disk);
    Computer creat();
}
