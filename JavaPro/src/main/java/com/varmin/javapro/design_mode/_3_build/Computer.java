package com.varmin.javapro.design_mode._3_build;

/**
 * Created by HuangYang
 * on 2018/12/8  18:53.
 * 文件描述：
 */
public class Computer {
    private String cpu;
    private String disk;
    private String memory;

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public void start() {
        System.out.println("Computer{" +
                "cpu='" + cpu + '\'' +
                ", disk='" + disk + '\'' +
                ", memory='" + memory + '\'' +
                '}');
    }
}
