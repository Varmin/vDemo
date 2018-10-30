package com.varmin.vdemo.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;

import com.varmin.vdemo.R;
import com.varmin.vdemo.base.Utils;

/**
 * Created by HuangYang
 * on 2018/10/30  14:40.
 * 文件描述：
 */
public class MaterialEditText extends AppCompatEditText {
    private static final String TAG = "MaterialEditText";
    private float LABEL_SPACE;
    private final float LABEL_TEXT_SIZE = Utils.dp2px(12);
    private boolean useFloatingLabel = true;
    private boolean isFloatingLabel = false;

    private final Paint mPaint;

    private float floatLabelFraction;

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(LABEL_TEXT_SIZE);
        mPaint.setColor(Color.RED);
        LABEL_SPACE = LABEL_TEXT_SIZE;
    }

    public MaterialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText);
        useFloatingLabel = typedArray.getBoolean(R.styleable.MaterialEditText_useFloatingLabel, true);
        typedArray.recycle();
        initView();
    }

    public void setUseFloatLabel(boolean isFloatingLabel){
        this.isFloatingLabel = isFloatingLabel;
        initView();
    }



    private void initView() {
        if (useFloatingLabel) {
            //todo padding backgroud 时序，关系
            setPadding(getPaddingLeft(), (int) (getPaddingTop()+LABEL_SPACE), getPaddingRight(), getPaddingBottom());

            addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void afterTextChanged(Editable s) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged: s="+s+", "+isFloatingLabel);
                    if (TextUtils.isEmpty(s) && isFloatingLabel) {
                        isFloatingLabel = false;
                        //close
                        getAnimator().reverse();
                    }else if(!TextUtils.isEmpty(s) && !isFloatingLabel){
                        isFloatingLabel = true;
                        //open
                        getAnimator().start();
                    }
                }
            });
        }
    }

    private ObjectAnimator objectAnim;
    public ObjectAnimator getAnimator(){
        if (objectAnim == null) {
            objectAnim = ObjectAnimator.ofFloat(this, "floatLabelFraction", 0, 1);
        }
        return objectAnim;
    }

    public float getFloatLabelFraction() {
        return floatLabelFraction;
    }

    public void setFloatLabelFraction(float floatLabelFraction) {
        this.floatLabelFraction = floatLabelFraction;
        Log.d(TAG, "setFloatLabelFraction: "+floatLabelFraction);
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (useFloatingLabel) {
            mPaint.setAlpha((int) (0xff * floatLabelFraction));
            float space = LABEL_SPACE + LABEL_TEXT_SIZE * (1 - floatLabelFraction);
            Log.d(TAG, "onDraw: "+isFloatingLabel+", "+floatLabelFraction+", "+space+", "+getHint().toString());
            canvas.drawText(getHint().toString(), 0, space, mPaint);
        }
    }
}
