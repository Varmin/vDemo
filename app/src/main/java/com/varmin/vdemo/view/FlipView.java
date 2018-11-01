package com.varmin.vdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
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
    private final int WIDTH = (int) Utils.dp2px(200);
    private final float defalutHeight = Utils.dp2px(200);
    private Bitmap mTarBitmap;

    public FlipView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mTarBitmap = Utils.getBitmap(context, (int) WIDTH, R.drawable.dog_500_500);
        setBackgroundResource(R.color.base_gray_bg_999);
    }


    private final Paint paint;
    private final Camera camera;

    {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        camera = new Camera();
        //camera.setLocation(0,0,-80);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(WIDTH, WIDTH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Log.e(TAG, "onDraw: "+getWidth()+", "+getHeight() );

        canvas.save();
        //canvas.clipRect(0, 0, getWidth(), BITMAP_HEIGHT/2);

        canvas.drawBitmap(mTarBitmap, 0, 0, paint);
        camera.setLocation(0,0,-800);
        camera.applyToCanvas(canvas);

        canvas.restore();
    }



}
