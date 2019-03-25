package com.varmin.vdemo.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by HuangYang
 * on 2018/10/26  17:09.
 * 文件描述：
 */

public class Utils {
    private static final String TAG = "Utils";
    private static AppActivityLifecycleCallback mActivityLifecyleCallback;

    public static void init(Application app){
        mActivityLifecyleCallback = new AppActivityLifecycleCallback();
        app.registerActivityLifecycleCallbacks(mActivityLifecyleCallback);
    }


    public static void startActivity(Class clazz){
        Context context = mActivityLifecyleCallback.getTopActivity();
        if (context == null) {
            context = App.getApplication();
        }
        startActivity(context, clazz);
    }
    public static void startActivity(Context context, Class clazz){
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }

    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    public static float dp2sp(float dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dp, Resources.getSystem().getDisplayMetrics());
    }

    public static Bitmap getBitmap(Context context, int width, int drawID){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), drawID, options);
        Log.e(TAG, "getBitmap: options="+options.outWidth+", "+options.outHeight+", "+width );
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), drawID, options);
        Log.e(TAG, "getBitmap: getWidth="+bitmap.getWidth()+", "+bitmap.getHeight() );

        return bitmap;
    }

    public static int[] getMeasureDefaultSize(int widthMeasureSpec, int heightMeasureSpec, int defaultWidhtSize, int defaultHeightSize){
        return new int[]{getMeasureDefaultSize(widthMeasureSpec, defaultWidhtSize), getMeasureDefaultSize(heightMeasureSpec, defaultHeightSize)};
    }

    public static int getMeasureDefaultSize(int measureSpec, int defaultSize){
        if (View.MeasureSpec.AT_MOST == View.MeasureSpec.getMode(measureSpec)) {
            return defaultSize;
        }
        return View.MeasureSpec.getSize(measureSpec);
    }


}
