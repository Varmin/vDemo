package com.varmin.vdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.varmin.vdemo.R;
import com.varmin.vdemo.base.Utils;

/**
 * Created by HuangYang
 * on 2018/10/27  16:46.
 * 文件描述：
 */
public class FlipView extends View {
    private static final String TAG = "FlipView";
    private final float WIDTH = Utils.dp2px(200);
    private final float defalutHeight = Utils.dp2px(200);
    private Bitmap mTarBitmap;

    public FlipView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mTarBitmap = Utils.getBitmap(context, (int) WIDTH);
        setBackgroundResource(R.color.base_gray_bg_999);
    }


    private final Paint paint;

    {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //todo ？？？有阴影但不是颜色
        //paint.setColor(0x99FF4081);
        Log.e(TAG, "instance initializer: WIDTH="+WIDTH );
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {
            setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), (int) defalutHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "onDraw: "+getWidth()+", "+getHeight() );

        //canvas.clipRect(getWidth()/2 - WIDTH/2, 0, getWidth()/2 + WIDTH/2, WIDTH/2);
        canvas.drawBitmap(mTarBitmap, getWidth()/2 - WIDTH/2, 0, paint);
        //canvas.drawRect(getWidth()/2 - WIDTH/2, WIDTH*2, getWidth()/2 + WIDTH/2, WIDTH*3, paint);
    }


}
