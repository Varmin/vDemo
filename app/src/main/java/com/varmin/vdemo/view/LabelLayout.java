package com.varmin.vdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.varmin.vdemo.base.Utils;

/**
 * Created by HuangYang
 * on 2018/11/1  11:15.
 * 文件描述：标签布局
 */
public class LabelLayout extends ViewGroup {
    //标签间隔
    private final int TABLE_SPACING = (int) Utils.dp2px(10);
    public LabelLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
