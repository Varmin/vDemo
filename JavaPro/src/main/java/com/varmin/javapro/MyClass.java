package com.varmin.javapro;

import com.varmin.javapro.design_mode.FactoryTestMain;

public class MyClass {
    public static void main(String[] args){
        System.out.println("hello world!");

        new FactoryTestMain().run();

        System.out.println("over!");
    }
}
