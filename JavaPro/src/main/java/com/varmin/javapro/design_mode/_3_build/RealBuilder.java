package com.varmin.javapro.design_mode._3_build;

/**
 * Created by HuangYang
 * on 2018/12/8  18:58.
 * 文件描述：
 */
public class RealBuilder implements Builder {
    private Computer computer;

    public static Builder builder(){
        return new RealBuilder()
                .build()
                .addCPU("自动装入CPU")
                .addDisk("自动装入硬盘")
                .addMemory("自动装入内存");
    }

    @Override
    public Builder build() {
        computer = new Computer();
        return this;
    }

    @Override
    public Builder addCPU(String cpu) {
        computer.setCpu(cpu);
        return this;
    }

    @Override
    public Builder addMemory(String memory) {
        computer.setMemory(memory);
        return this;
    }

    @Override
    public Builder addDisk(String disk) {
        computer.setDisk(disk);
        return this;
    }

    @Override
    public Computer creat() {
        return computer;
    }
}
