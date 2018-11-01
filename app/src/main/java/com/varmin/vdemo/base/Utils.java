package com.varmin.vdemo.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.TypedValue;

import com.varmin.vdemo.R;

/**
 * Created by HuangYang
 * on 2018/10/26  17:09.
 * 文件描述：
 */

public class Utils {
    private static final String TAG = "Utils";
    public static void startActivity(Activity activity, Class clazz){
        Intent intent = new Intent(activity, clazz);
        activity.startActivity(intent);
    }

    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
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
}
