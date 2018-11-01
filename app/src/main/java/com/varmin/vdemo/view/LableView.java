package com.varmin.vdemo.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.varmin.vdemo.R;

/**
 * Created by HuangYang
 * on 2018/11/1  11:18.
 * 文件描述：
 */
public class LableView extends View {
    private static final String TAG = "LableView";
    private String text;
    private int textSize;
    private Paint mPaint;
    private int roundRadius;
    private Rect rect;
    private ColorStateList textColor;
    private Paint.FontMetrics metrics;

    public LableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.LableView);
        text = typeArray.getString(R.styleable.LableView_android_text);
        textSize = typeArray.getDimensionPixelSize(R.styleable.LableView_android_textSize, 15);
        textColor = typeArray.getColorStateList(R.styleable.LableView_android_textColor);
        roundRadius = typeArray.getDimensionPixelSize(R.styleable.LableView_round_radius,0);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(textSize);
        if (textColor == null) textColor = ColorStateList.valueOf(0xFF000000);
        rect = new Rect();
        setTextBounds();
    }

    private void setTextBounds( ) {
        if (!TextUtils.isEmpty(text)) {
            mPaint.setTextAlign(Paint.Align.LEFT);
            mPaint.getTextBounds(text, 0, text.length(), rect);
            metrics = mPaint.getFontMetrics();
            Log.e(TAG, "setTextBounds: "+metrics.descent+", "+metrics.ascent );
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(rect.right-rect.left+getPaddingLeft()+getPaddingRight(), (int) (metrics.descent - metrics.ascent+getPaddingTop()+getPaddingBottom()));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.LTGRAY);
        canvas.drawRoundRect(0,0,getMeasuredWidth(), getMeasuredHeight(), roundRadius,roundRadius,mPaint);
        mPaint.setColor(textColor.getColorForState(getDrawableState(),0));
        canvas.drawText(text, getPaddingLeft(), getMeasuredHeight()- metrics.descent - getPaddingBottom(), mPaint);
    }
}
