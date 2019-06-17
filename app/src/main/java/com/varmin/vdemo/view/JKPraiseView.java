package com.varmin.vdemo.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.varmin.vdemo.R;
import com.varmin.vdemo.utils.SystemUtil;

/**
 * Created by HuangYang
 * on 2019-06-03  21:13.
 * 文件描述：
 */
public class JKPraiseView extends View {
    private static final String TAG = "JKPraiseView";
    private int likeNumber;
    private Paint bitmapPaint;
    private Bitmap unLikeBitmap;
    private Bitmap likeBitmap;
    private Bitmap shiningBitmap;
    private Context mContext;
    private Paint textPaint;
    private Paint oldTextPaint;
    private boolean isLike;
    private float handScale = 1.0f;
    private float shiningScale;
    private long duration = 250;

    public JKPraiseView(Context context) {
        this(context, null);
    }

    public JKPraiseView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public JKPraiseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        initView();
    }

    private void initAttrs(AttributeSet attrs) {
        mContext = getContext();
        TypedArray typeArray = mContext.obtainStyledAttributes(attrs, R.styleable.JiKePraiseView);
        likeNumber = typeArray.getInt(R.styleable.JiKePraiseView_praise_num, 990);
        Log.d(TAG, "initAttrs: "+likeNumber);
        typeArray.recycle();
    }

    @SuppressLint("ResourceType")
    private void initView() {
        //bitmapPaint是图像画笔
        bitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        oldTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.GRAY);
        textPaint.setTextSize(SystemUtil.sp2px(getContext(), 14));
        oldTextPaint.setColor(Color.GRAY);
        oldTextPaint.setTextSize(SystemUtil.sp2px(getContext(), 14));

        //todo error
//        setBackgroundResource(mContext.getResources().getColor(R.color.base_app_alert_bg));
        setBackgroundColor(Color.GRAY);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow: ");
        Resources resources = getResources();
        unLikeBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_message_unlike);
        likeBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_message_like);
        shiningBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_message_like_shining);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow: ");
        //回收bitmap
        unLikeBitmap.recycle();
        likeBitmap.recycle();
        shiningBitmap.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        String likeNumStr = String.valueOf(likeNumber);
        float likeNumWidth = textPaint.measureText(likeNumStr);
        widthMeasureSpec = MeasureSpec.makeMeasureSpec((int) (likeBitmap.getHeight() + likeNumWidth + SystemUtil.dp2px(mContext, 30)), MeasureSpec.EXACTLY);

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(likeBitmap.getHeight() + SystemUtil.dp2px(mContext, 20), MeasureSpec.EXACTLY);
        Log.d(TAG, "onMeasure: "+MeasureSpec.getSize(widthMeasureSpec)+", "+MeasureSpec.getSize(heightMeasureSpec));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //TODO 如果说16毫秒的话，为什么重绘这么少？而且是好多个动画都会调用？
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getMeasuredHeight();
        int centY = height / 2;
        //画哪个图片
        Bitmap handBitmap = isLike ? likeBitmap : unLikeBitmap;
        int handBitmapWidth = handBitmap.getWidth();
        int handBitmapHeight = handBitmap.getHeight();

        //TODO 图片资源放到不同文件夹，是否会有偏差？
        int originX = SystemUtil.dp2px(mContext, 10);

        //画小手
        int handTop = (height - handBitmapHeight)/2;
        canvas.save();
        canvas.scale(handScale, handScale, originX + handBitmapWidth/2, centY);
        canvas.drawBitmap(handBitmap, originX, handTop, bitmapPaint);
        canvas.restore();

        //画闪亮点
        int shiningTop = handTop - shiningBitmap.getHeight() + SystemUtil.dp2px(mContext, 17);
        canvas.save();
        canvas.scale(shiningScale, shiningScale, originX + handBitmapWidth / 2, handTop);
        if (isLike) {
            canvas.drawBitmap(shiningBitmap, SystemUtil.dp2px(mContext, 15), shiningTop, bitmapPaint);
        }else {
            bitmapPaint.setAlpha(0);
            canvas.drawBitmap(shiningBitmap, SystemUtil.dp2px(getContext(), 15), shiningTop, bitmapPaint);
            bitmapPaint.setAlpha(255);
        }
        canvas.restore();

        //画圆环
        if (isLike) {
            canvas.save();
            canvas.scale(shiningScale, shiningScale, handBitmapWidth/2 + SystemUtil.dp2px(mContext, 10), centY);
            canvas.drawCircle(handBitmapWidth/2 + SystemUtil.dp2px(mContext, 10), centY, handBitmapWidth/2, bitmapPaint);
            canvas.restore();
        }




    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                jump();
                break;
        }
        return super.onTouchEvent(event);
    }

    private void jump() {
        isLike = !isLike;
        if (isLike) {
            likeNumber++;

            ObjectAnimator handScaleAnim = ObjectAnimator.ofFloat(this, "handScale", 1f, 0.8f, 1f);
            ObjectAnimator shingScaleAnim = ObjectAnimator.ofFloat(this, "shingScale", 0f, 1f);

            AnimatorSet animSet = new AnimatorSet();
            animSet.play(handScaleAnim).with(shingScaleAnim);
            animSet.setDuration(duration);
            animSet.start();
        }else {
            likeNumber--;
            ObjectAnimator handScaleAnim = ObjectAnimator.ofFloat(this, "handScale", 1f, 0.8f, 1f);
            handScaleAnim.setDuration(duration);
            handScaleAnim.start();

        }
    }

    public void setHandScale(float handScale) {
        this.handScale = handScale;
        invalidate();
    }


    public void setShingScale(float shiningScale) {
        this.shiningScale = shiningScale;
        invalidate();
    }
}
