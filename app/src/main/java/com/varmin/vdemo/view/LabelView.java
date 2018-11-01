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
 * 文件描述:本想让控件完全包裹文字没有任何间距，由于文字排版的
 */
public class LabelView extends View {
    private static final String TAG = "LableView";
    private String text;
    private int textSize;
    private Paint mPaint;
    private int roundRadius;
    private Rect rect;
    private ColorStateList textColor;
    private Paint.FontMetrics metrics;
    private boolean isBounds = true;

    public LabelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.LabelView);
        text = typeArray.getString(R.styleable.LableView_android_text);
        textSize = typeArray.getDimensionPixelSize(R.styleable.LableView_android_textSize, 15);
        textColor = typeArray.getColorStateList(R.styleable.LableView_android_textColor);
        roundRadius = typeArray.getDimensionPixelSize(R.styleable.LabelView_round_radius,0);
        isBounds = typeArray.getBoolean(R.styleable.LabelView_is_bound, true);
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
            Log.e(TAG, "setTextBounds: "+metrics.bottom+", "+metrics.descent+", "+metrics.ascent+", "+metrics.top );
            Log.d(TAG, "setTextBounds: "+rect.left+", "+rect.top+", "+rect.right+", "+rect.bottom);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (isBounds) {
             setMeasuredDimension(rect.right-rect.left+getPaddingLeft()+getPaddingRight(), (rect.bottom - rect.top +getPaddingTop()+getPaddingBottom()));
        }else {
            setMeasuredDimension(rect.right-rect.left+getPaddingLeft()+getPaddingRight(), (int) (metrics.descent - metrics.ascent+getPaddingTop()+getPaddingBottom()));
        }
        Log.d(TAG, "onMeasure: "+getMeasuredWidth()+", "+getMeasuredHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isBounds) {
            mPaint.setColor(0xff40929C);
        }else {
            mPaint.setColor(0x55FF4081);
        }
        canvas.drawRoundRect(0,0,getMeasuredWidth(), getMeasuredHeight(), roundRadius,roundRadius,mPaint);
        mPaint.setColor(textColor.getColorForState(getDrawableState(),0));

        if (isBounds) {
            canvas.drawText(text, getPaddingLeft(), getMeasuredHeight()- getPaddingBottom(), mPaint);
        }else {
            canvas.drawText(text, getPaddingLeft(), getMeasuredHeight()- metrics.descent - getPaddingBottom(), mPaint);

            float bottomLineY = getMeasuredHeight() - getPaddingBottom() - metrics.bottom;
            canvas.drawLine(0, bottomLineY, getMeasuredWidth()-getPaddingRight(), bottomLineY, mPaint);

            float descentLineY = getMeasuredHeight() - getPaddingBottom() - metrics.descent;
            mPaint.setColor(Color.RED);
            canvas.drawLine(0, descentLineY, getMeasuredWidth()-getPaddingRight(), descentLineY, mPaint);

            float ascentLineY = getMeasuredHeight() - getPaddingBottom() + metrics.ascent;
            mPaint.setColor(Color.GREEN);
            canvas.drawLine(0, ascentLineY, getMeasuredWidth()-getPaddingRight(), ascentLineY, mPaint);

            float topLineY = getMeasuredHeight() - getPaddingBottom() + metrics.top;
            mPaint.setColor(Color.BLUE);
            canvas.drawLine(0, topLineY, getMeasuredWidth()-getPaddingRight(), topLineY, mPaint);
        }
    }
}
