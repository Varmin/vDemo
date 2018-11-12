package com.varmin.vdemo.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;

import com.varmin.vdemo.R;
import com.varmin.vdemo.base.Utils;

/**
 * Created by HuangYang
 * on 2018/11/11  20:46.
 * 文件描述：
 * 缩放-动画-偏移
 * todo: onSizeChanged时序、页面后台前台会有哪些方法调用、Gesture、Scroller、OverScroller
 */

/**
 * OverScroller:
 * OverScroller和Scroller区别：Scroller的flling很慢？
 * postOnAnimation 下一帧执行。 和 post(runnable)的区别
 * filling框：模型！！！
 * 原来写的startScroller和scroller.filling区别
 */

/**
 * 横向滑动View：重写
 */
public class ScalableImageView extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    private static final String TAG = "ScalableImageView";
    private final Context mContext;
    private Paint mPaint;
    private final float WIDTH = Utils.dp2px(300);
    private Bitmap bitmap;
    private float originalOffsetX;
    private float originalOffsetY;
    private float offsetX;
    private float offsetY;
    private boolean isBig;
    private float smallScale;
    private float bigScale;
    private float currentScale;
    private final float OVER_SCALE_FACTOR = 1.5f;
    private GestureDetector gestureDetecor;
    private OverScroller overScroller;


    public ScalableImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initAttrs(attrs);
        initView();
    }
    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.ScalableImageView);

    }

    private void initView() {
        Log.d(TAG, "initView: ");
        bitmap = Utils.getBitmap(mContext, (int) WIDTH, R.drawable.dog);
        setBackgroundResource(R.color.base_view_bg);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        gestureDetecor = new GestureDetector(mContext, this);
        gestureDetecor.setIsLongpressEnabled(false);
        overScroller = new OverScroller(mContext);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure: "+MeasureSpec.getSize(widthMeasureSpec)+", "+MeasureSpec.getSize(heightMeasureSpec));
        /*int [] size = Utils.getMeasureDefaultSize(widthMeasureSpec, heightMeasureSpec, (int) WIDTH, (int) WIDTH);
        Log.d(TAG, "onMeasure: sizes="+size[0]+", "+size[1]);
        //这里用填充满屏幕
        setMeasuredDimension(size[0], size[1]);*/
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "onSizeChanged: ");
        originalOffsetX = (float) (getWidth()-bitmap.getWidth())/2;
        originalOffsetY = (float) (getHeight()-bitmap.getHeight())/2;

        if((float)bitmap.getWidth()/bitmap.getHeight() > (float)getWidth()/getHeight()){//图片比较胖
            smallScale = (float) bitmap.getWidth() / getWidth();
            bigScale = (float) getWidth() / bitmap.getWidth() * OVER_SCALE_FACTOR;
        }else {
            smallScale = (float) bitmap.getHeight() / getHeight();
            bigScale = (float) getHeight() / bitmap.getHeight() * OVER_SCALE_FACTOR;
        }

        smallScale = smallScale < 1 ? 1 : smallScale;
        currentScale = isBig ? bigScale : smallScale;

        Log.d(TAG, "onSizeChanged: smallScale="+smallScale+", bigScale="+bigScale+", currenScale="+currentScale);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw: currentScale="+currentScale+", "+offsetX+", "+offsetY);

        float scaleFraction = (currentScale - smallScale) / (bigScale - smallScale);
        offsetX = offsetX * scaleFraction;
        offsetY = offsetY * scaleFraction;
        canvas.translate(offsetX, offsetY);
        canvas.scale(currentScale, currentScale, getWidth()/2, getHeight()/2);
        canvas.drawBitmap(bitmap, originalOffsetX, originalOffsetY, mPaint);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, "dispatchTouchEvent: ");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: ");
        return gestureDetecor.onTouchEvent(event);
    }

    // ---------------------OnGestureDetector begin------------------------------
    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent down, MotionEvent event, float distanceX, float distanceY) {
        if (isBig) {
            offsetX -= distanceX;
            offsetY -= distanceY;
            Log.d(TAG, "onScroll: "+offsetX+", "+offsetY);
            fixOffsets();

            invalidate();
        }
        return false;
    }


    private void fixOffsets() {
        if (bitmap.getWidth()*bigScale - getWidth() > 0) {
            offsetX = Math.min(offsetX, (bitmap.getWidth() * bigScale - getWidth()) / 2);
            offsetX = Math.max(offsetX, - (bitmap.getWidth() * bigScale - getWidth()) / 2);
        }else {
            offsetX = 0;
        }
        if (bitmap.getHeight()*bigScale - getHeight() > 0) {
            offsetY = Math.min(offsetY, (bitmap.getHeight() * bigScale - getHeight()) / 2);
            offsetY = Math.max(offsetY, - (bitmap.getHeight() * bigScale - getHeight()) / 2);
        }else {
            offsetY = 0;
        }
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    //---------------------OnGestureDetector end------------------------------

   //---------------------OnDoubleTapListener begin------------------------------
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        //todo 放大偏移后，缩小偏移量
        Log.d(TAG, "onDoubleTap: "+offsetX+", "+offsetY);
        isBig = !isBig;
        if (isBig) {
            getAnimator().start();
        }else {
            Log.d(TAG, "onDoubleTap: "+offsetX+", "+offsetY);
            getAnimator().reverse();
        }
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }
    //---------------------OnDoubleTapListener end------------------------------

    private ObjectAnimator getAnimator(){
        ObjectAnimator scaleAnim = ObjectAnimator.ofFloat(this, "currentScale", smallScale, bigScale);
        return scaleAnim;
    }
    private void setCurrentScale(float currentScale){
        this.currentScale = currentScale;
        invalidate();
    }
    private float getCurrentScale(){
        return currentScale;
    }

}
