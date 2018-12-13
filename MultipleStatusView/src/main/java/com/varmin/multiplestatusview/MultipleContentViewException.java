package com.varmin.multiplestatusview;

/**
 * Created by HuangYang
 * on 2018/12/12  23:29.
 * 文件描述：
 */
public class MultipleContentViewException extends RuntimeException {
    public MultipleContentViewException() {
        this("ContentView more than one ! Only need one.");
    }

    public MultipleContentViewException(String message) {
        super(message);
    }
}
