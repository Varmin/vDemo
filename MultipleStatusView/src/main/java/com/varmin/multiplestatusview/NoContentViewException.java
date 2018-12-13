package com.varmin.multiplestatusview;

/**
 * Created by HuangYang
 * on 2018/12/12  23:29.
 * 文件描述：
 */
public class NoContentViewException extends RuntimeException {
    public NoContentViewException() {
        this("No ContentView Exception");
    }

    public NoContentViewException(String message) {
        super(message);
    }
}
