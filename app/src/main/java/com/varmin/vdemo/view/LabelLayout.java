package com.varmin.vdemo.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.varmin.vdemo.base.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuangYang
 * on 2018/11/1  11:15.
 * 文件描述：标签布局
 * 在onMeasure测量自己的时候把子View坐标保存，这样就可以onLayout的时候直接用了。
 */
public class LabelLayout extends ViewGroup implements View.OnClickListener {
    private static final String TAG = "LabelLayout";
    //标签间隔
    private int tableSpacingHorizontal = (int) Utils.dp2px(10);
    private int tableSpacingVertical = (int) Utils.dp2px(5);

    private final Context mContext;
    private List<Rect> childRects = new ArrayList<>();
    private String[] mLabels;
    private OnLabelClickListener mOnLabelClickListener;

    public LabelLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    /**
     * 初始化标签数组
     */
    public void setLabels(String[] labels){
        this.mLabels = labels;
        if (mLabels != null) {
            for (String label : mLabels) {
                LabelView labelView = new LabelView(mContext, label, false);
                labelView.setOnClickListener(this);
                addView(labelView);
            }
            invalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        Log.d(TAG, "onMeasure: padding="+getPaddingLeft()+", spaceHor="+tableSpacingHorizontal+", spaceVertical="+tableSpacingVertical);
        int linesCount = 0;
        int useWidth = getPaddingLeft();
        int useHeight = getPaddingTop();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            Rect rect = new Rect();
            childRects.add(rect);

            int cw = child.getMeasuredWidth();
            int ch = child.getMeasuredHeight();
            Log.e(TAG, "onMeasure: useWidth="+useWidth+", cw="+cw+", ch="+ch+", sapce="+tableSpacingHorizontal );
            if((useWidth + cw + tableSpacingHorizontal) > wSize){
                linesCount ++;
                useWidth = getPaddingLeft();
                useHeight += (ch + tableSpacingVertical);
            }
            rect.left = useWidth;
            rect.top = useHeight;
            useWidth += (cw + tableSpacingHorizontal);
            rect.right = useWidth;
            rect.bottom = useHeight + ch;
        }
        if (getChildCount() != 0) {
            useHeight += (getChildAt(0).getMeasuredHeight()+getPaddingBottom());
        }
        if (getChildCount() > 0) {
            Log.d(TAG, "onMeasure: childSize="+getChildCount()+", childHeight="+getChildAt(0).getMeasuredHeight()
                    +", lines="+linesCount+", "+", width="+wSize+", height="+useHeight);
        }
        for (Rect childRect : childRects) {
            Log.d(TAG, "childRect: "+childRect.left+", "+childRect.top+", "+childRect.right+", "+childRect.bottom);
        }
        setMeasuredDimension(wSize, useHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            Rect rect = childRects.get(i);
            getChildAt(i).layout(rect.left, rect.top, rect.right, rect.bottom);
        }
    }

    @Override
    public void onClick(View v) {
        if (mOnLabelClickListener != null) {
            LabelView labelView = (LabelView) v;
            String label = labelView.getText();
            mOnLabelClickListener.labelClick(label);
        }
    }

    public interface OnLabelClickListener{
        void labelClick(String label);
    }
    public void setOnLabelClickListener(OnLabelClickListener onLabelClickListener){
        this.mOnLabelClickListener = onLabelClickListener;
    }
}
