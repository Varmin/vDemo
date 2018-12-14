package com.varmin.multiplestatusview;


import android.view.View;

/**
 * Created by HuangYang
 * on 2018/12/12  16:07.
 * 文件描述：
 */
public interface MultipleStatus {
    /**
     * 显示主View，即内容
     */
    void showContentView();

    /**
     * 显示各种状态View
     */
    void showStatusView(View view);


    void showRefresh();
    void showRefresh(int layoutId);

    void showLoading();
    void showLoading(int layoutId);

    void showEmpty();
    void showEmpty(int layoutId);
    void showErrNet();
    void showErrNet(int layoutId);
    void showError();
    void showError(int layoutId);
    void showSuccess();
    void showSuccess(int layoutId);

    void setRetryOnclickListener(OnRetryListener onRetryListener);

}
