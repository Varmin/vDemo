package com.varmin.javapro;

import com.varmin.javapro.design_mode._1_factory.FactoryTestMain;
import com.varmin.javapro.design_mode._2_proxy.ProxyMain;

public class MyClass {
    public static void main(String[] args){
        System.out.println("------------JavaPro start...------------");

        //new FactoryTestMain().run();
        new ProxyMain().run();


        System.out.println("------------JavaPro over!!!------------");
    }
}
