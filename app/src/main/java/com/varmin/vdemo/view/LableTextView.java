package com.varmin.vdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by HuangYang
 * on 2018/11/1  11:21.
 * 文件描述：去掉TextView内部间隔
 */
public class LableTextView extends AppCompatTextView {
    private static final String TAG = "LableTextView";
    public LableTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private final Paint mPaint;

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mPaint.setTextSize(getTextSize());
        Rect bound = new Rect();
        mPaint.getTextBounds(getText().toString(), 0, getText().toString().length(), bound);

        Log.d(TAG, "onDraw:"+getMeasuredWidth()+", "+getMeasuredHeight());
        Log.d(TAG, "onDraw: w="+(bound.right-bound.left)+", h="+(bound.bottom - bound.top));
        //setMeasuredDimension(bound.right-bound.left, bound.bottom - bound.top);
        Log.d(TAG, "onDraw:"+getMeasuredWidth()+", "+getMeasuredHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
