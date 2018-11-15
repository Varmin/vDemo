package com.varmin.vdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.varmin.vdemo.R;
import com.varmin.vdemo.base.Utils;

/**
 * Created by HuangYang
 * on 2018/11/14  22:17.
 * 文件描述：多点触控
 * <p>
 * 1. MOVE中获取的index始终为0
 * 2. index遍历，id追踪
 * 3. 每一个事件都可以有很多个index的记录。
 * 4. index，id复用规则：
 * ---a. 下一个事件中更新
 * ---b. 本身有记录只更新index，没记录复用就(index,id)，没记录也没可复用则按序增加。
 */


/**
 * 抬起时的变化：a,b,c三根手指（0，0）(1,1), (2,2)
 * b抬起：
 *   此时获取的还是3根手指个数，a,b,c都还是原对应的index、id。
 *   1. c滑动： (1,2)，c的index更新。
 *   2. c抬起：（1,2），c的index更新。
 *   3. ac不动，e落下： (1,1)，a,c不变。
 * index, id, pointCount 的更新是在下个事件
 * 更新时：
 *  自己有index,id记录的，只更新index。
 *  没有记录的，复用原来的index,id。 没有可复用的则按序增加。
 */
public class MutilPointTouchView extends View {
    private static final String TAG = "MutilPointTouchView";
    private final Context mContext;
    private final int WIDTH = (int) Utils.dp2px(300);
    private Bitmap bitmap;
    private Paint mPaint;
    private float offsetX;
    private float offsetY;
    private int tracePointId;
    private float originOffsetX;
    private float originOffsetY;
    private float downX;
    private float downY;

    public MutilPointTouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    private void initView() {
        bitmap = Utils.getBitmap(mContext, WIDTH, R.drawable.dog_500_500);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        offsetX = (float) (getWidth() - bitmap.getWidth()) / 2;
        offsetY = (float) (getHeight() - bitmap.getHeight()) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, offsetX, offsetY, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getActionMasked()) {
            //第一个手指按下
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent: ACTION_DOWN=" + event.getActionIndex() + ", " + event.getPointerId(event.getActionIndex()));
                tracePointId = event.getPointerId(event.getActionIndex());
                originOffsetX = offsetX;
                originOffsetY = offsetY;
                downX = event.getX(event.getActionIndex());
                downY = event.getY(event.getActionIndex());
                break;
            //move每次获取的index都为0
            //所有的move事件，单指多指
            case MotionEvent.ACTION_MOVE:
                //Log.d(TAG, "onTouchEvent: ACTION_MOVE="+event.getActionIndex()+", "+event.getPointerId(event.getActionIndex()));
                int curIndex = event.findPointerIndex(tracePointId);
                Log.d(TAG, "onTouchEvent: curIndex=" + curIndex + ", curId=" + tracePointId);
                //当前位置 - down位置 + 初始偏移
                offsetX = event.getX(curIndex) - downX + originOffsetX;
                offsetY = event.getY(curIndex) - downY + originOffsetY;

                invalidate();
                break;
            //最后一个手指抬起
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent: ACTION_UP=" + event.getActionIndex() + ", " + event.getPointerId(event.getActionIndex()));
                break;
            //非主要手指按下
            case MotionEvent.ACTION_POINTER_DOWN:
                Log.d(TAG, "onTouchEvent: ACTION_POINTER_DOWN=" + event.getActionIndex() + ", " + event.getPointerId(event.getActionIndex()));
                tracePointId = event.getPointerId(event.getActionIndex());
                originOffsetX = offsetX;
                originOffsetY = offsetY;
                downX = event.getX(event.getActionIndex());
                downY = event.getY(event.getActionIndex());
                break;

            /**
             * 抬起时的变化：a,b,c三根手指（0，0）(1,1), (2,2)
             * b抬起：
             *   此时获取的还是3根手指个数，a,b,c都还是原对应的index、id。
             *   1. c滑动： (1,2)，c的index更新。
             *   2. c抬起：（1,2），c的index更新。
             *   3. ac不动，e落下： (1,1)，a,c不变。
             * index, id, pointCount 的更新是在下个事件
             * 更新时：
             *  自己有index,id记录的，只更新index。
             *  没有记录的，复用原来的index,id。 没有可复用的则按序增加。
             */
            //非主要手指抬起
            case MotionEvent.ACTION_POINTER_UP:
                Log.d(TAG, "onTouchEvent: ACTION_POINTER_UP=" + event.getActionIndex() + ", " + event.getPointerId(event.getActionIndex()));
                for (int i = 0; i < event.getPointerCount(); i++) {
                    int indexID = event.getPointerId(i);
                    Log.e(TAG, "onTouchEvent: index=" + i + ", id=" + indexID);
                }

                //假如追踪的id抬起了，再寻找其它id
                int newIndex = -1;
                int curUpIndex = event.getActionIndex();
                int curPointId = event.getPointerId(curUpIndex);
                if (curPointId == tracePointId) {
                    for (int i = 0; i < event.getPointerCount(); i++) {
                        if (i != curUpIndex) {
                            newIndex = i;
                            tracePointId = event.getPointerId(i);
                            break;
                        }
                    }
                    originOffsetX = offsetX;
                    originOffsetY = offsetY;
                    downX = event.getX(newIndex);
                    downY = event.getY(newIndex);
                } else {
                }
                break;

        }


        return true;
    }

}
