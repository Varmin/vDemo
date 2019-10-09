package com.varmin.vdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

/**
 * Created by HuangYang
 * on 2019-08-22  16:20.
 * 文件描述：标签流式布局
 */
public class FlowLabelLayout<T> extends ViewGroup {
    private static final String TAG = "FlowLayout";
    private final Context mContet;
    /**
     * 自定义View
     * 默认View
     */
    private LayoutParams mChildParams;
    private View mChildView;
    private List<String> mLabels;

    public FlowLabelLayout(Context context) {
        this(context, null);
    }

    public FlowLabelLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLabelLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContet = context;
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        Log.d(TAG, "initView: " + getChildCount());
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow: " + getChildCount());
        if (true) {//todo 默认、自定义, 都使用childParmas命名
            if (getChildCount() != 0) {
                mChildView = getChildAt(0);
                mChildParams = mChildView.getLayoutParams();
                Log.d(TAG, "onAttachedToWindow: " + mChildView.getClass().getName()+", "+mChildParams.width + ", " + mChildParams.height + ", " + mChildParams.toString());
            }
        }else{

        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure: " + getChildCount());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d(TAG, "onLayout: " + getChildCount());
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>---测量、布局--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>---methods--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public void setLabels(List<String> labels) {
        this.mLabels = labels;

    }
}
