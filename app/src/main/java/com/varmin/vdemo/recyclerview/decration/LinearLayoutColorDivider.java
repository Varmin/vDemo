package com.varmin.vdemo.recyclerview.decration;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.v7.widget.LinearLayoutManager;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;

public class LinearLayoutColorDivider extends RecyclerView.ItemDecoration {
    private static final String TAG = "LinearLayoutColorDivide";
    private final Drawable mDivider;
    private final Drawable mDividerDefault = new ColorDrawable(Color.GRAY);
    private final int mSize;
    private final int mOrientation;
    private boolean isDraw = true;
    private boolean isOverDraw = false;
    private int lastChildCount;


    public LinearLayoutColorDivider(Resources resources, @ColorRes int color,
                                    @DimenRes int size, int orientation) {
        mDivider = new ColorDrawable(resources.getColor(color));
        mSize = resources.getDimensionPixelSize(size)*2;
        mOrientation = orientation;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        Log.d(TAG, "onDrawOver:----------------------------");
        if (isOverDraw) {
            draw(c,parent);
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        Log.d(TAG, "onDraw:----------------------------");
        if (!isOverDraw) {
            draw(c, parent);
        }
    }

    /**
     * Recyclerview，draw调用
     */
    private void draw(Canvas c, RecyclerView parent) {
        if (isDraw) {
            int left;
            int right;
            int top;
            int bottom;
            if (mOrientation == LinearLayoutManager.HORIZONTAL) {
                top = parent.getPaddingTop();
                bottom = parent.getHeight() - parent.getPaddingBottom();
                final int childCount = parent.getChildCount();
                for (int i = 0; i < childCount - 1; i++) {
                    final View child = parent.getChildAt(i);
                    final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                    left = child.getRight() + params.rightMargin;
                    right = left + mSize;
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(c);
                }
            } else {
                left = parent.getPaddingLeft();
                right = parent.getWidth() - parent.getPaddingRight();
                final int childCount = parent.getChildCount();
                /*for (int i = 0; i < childCount; i++) {
                    final View child = parent.getChildAt(i);
                    final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                    top = child.getBottom() + params.bottomMargin;
                    bottom = top + mSize;
                    if (i % 2 == 0) {
                        mDivider.setBounds(right-mSize, child.getTop(), right, child.getBottom());
                        mDivider.draw(c);
                        mDivider.setBounds(left, top, right, bottom);
                        mDivider.draw(c);
                    }else {
                        mDivider.setBounds(left, child.getTop(), left+mSize, child.getBottom());
                        mDivider.draw(c);
                        mDivider.setBounds(left, top, right, bottom);
                        mDivider.draw(c);
                    }
                    Rect rect = mDivider.getBounds();
                    Log.d(TAG, "draw: i=" + i + ", "+rect.toShortString()+", tag="+child.getTag()+", childCount="+parent.getChildCount());
                }*/

                for (int i = 0; i < childCount; i++) {
                    final View child = parent.getChildAt(i);
                    final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                    top = child.getBottom() + params.bottomMargin;
                    bottom = top + mSize;

                    /**
                     * if (i % 2 == 0)
                     * 如果以i来判断，上下滑动时childCount会变
                     * 原来的奇数可能就会变成偶数了，会出现该item画的有所变化
                     * 以其它标识来判断如何画
                     *
                     * todo 上下多滑动几次会出现bug，原因是复用了View导致奇偶数相连的两个item复用的hodler正好tag都是奇数或偶数--> 以其它判断
                     */
                    if (((int)child.getTag()) %2 == 0) {
                        mDivider.setBounds(right-mSize, child.getTop(), right, child.getBottom());
                        mDivider.draw(c);
                        mDivider.setBounds(left, top, right, bottom);
                        mDivider.draw(c);
                    }else {
                        mDivider.setBounds(left, child.getTop(), left+mSize, child.getBottom());
                        mDivider.draw(c);
                        mDivider.setBounds(left, top, right, bottom);
                        mDivider.draw(c);
                    }
                    Rect rect = mDivider.getBounds();
                    Log.d(TAG, "draw: i=" + i + ", "+rect.toShortString()+", tag="+child.getTag()+", childCount="+parent.getChildCount());
                }
            }
        }
    }

    /**
     * 子View的偏移
     * 测量每个子View的时候调用
     */

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        Log.d(TAG, "getItemOffsets:----------------------------tag="+view.getTag()+", childCout="+parent.getChildCount());
        if (mOrientation == LinearLayoutManager.HORIZONTAL) {
            outRect.set(0, 0, mSize, 0);
        } else {
            if (((int)view.getTag()) %2 == 0) {
                outRect.set(0, 0, mSize, mSize);
            }else {
                outRect.set(mSize, 0, 0, mSize);
            }
        }
    }
}