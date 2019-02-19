package com.varmin.vdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by HuangYang
 * on 2019/2/13  11:25.
 * 文件描述：
 */
public class BaseLifeActivity extends AppCompatActivity {
    public static String TAG = "BaseLifeActivity";
    private boolean mIsLog = true;
    public boolean isLog(boolean isLog) {
        this.mIsLog = isLog;
        return mIsLog;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        TAG = getClass().getSimpleName();
        if (mIsLog) Log.d(TAG, "onCreate: ----------------begin");
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
        if (mIsLog) Log.d(TAG, "onCreate: ----------------end");
    }

    @Override
    protected void onStart() {
        if (mIsLog) Log.d(TAG, "onStart: ----------------begin");
        super.onStart();
        if (mIsLog) Log.d(TAG, "onStart: ----------------end");
    }

    @Override
    protected void onResume() {
        if (mIsLog) Log.d(TAG, "onResume: ----------------begin");
        super.onResume();
        if (mIsLog) Log.d(TAG, "onResume: ----------------end");
    }

    @Override
    protected void onPause() {
        if (mIsLog) Log.d(TAG, "onPause: ----------------begin");
        super.onPause();
        if (mIsLog) Log.d(TAG, "onPause: ----------------end");
    }

    @Override
    protected void onStop() {
        if (mIsLog) Log.d(TAG, "onStop: ----------------begin");
        super.onStop();
        if (mIsLog) Log.d(TAG, "onStop: ----------------end");
    }

    @Override
    protected void onDestroy() {
        if (mIsLog) Log.d(TAG, "onDestroy: ----------------begin");
        super.onDestroy();
        if (mIsLog) Log.d(TAG, "onDestroy: ----------------end");
    }
}
