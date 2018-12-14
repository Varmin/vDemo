package com.varmin.multiplestatusview;

import android.view.View;

/**
 * Created by HuangYang
 * on 2018/12/13  22:37.
 * 文件描述：
 */
public interface OnRetryListener {
    void onRetryClick(@Status String status, View view);
}
