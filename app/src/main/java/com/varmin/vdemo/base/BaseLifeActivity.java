package com.varmin.vdemo.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by HuangYang
 * on 2019/2/13  11:25.
 * 文件描述：
 */
public class BaseLifeActivity extends AppCompatActivity {
    public String TAG = "BaseLifeActivity";
    private boolean mIsLog = true;
    private boolean mIsAll = false;
    public void isLog(boolean isLog) {
        isLog(isLog, true);
    }
    public void isAll(boolean isAll) {
        isLog(true, isAll);
    }
    public void isLog(boolean isLog, boolean isAll) {
        this.mIsLog = isLog;
        this.mIsAll = isAll;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        TAG = getClass().getSimpleName();
        if (mIsLog) Log.d(TAG, "onCreate: ----------------begin");
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
        if (mIsLog && mIsAll) Log.d(TAG, "onCreate: ----------------end");
    }

    @Override
    protected void onStart() {
        if (mIsLog) Log.d(TAG, "onStart: ----------------begin");
        super.onStart();
        if (mIsLog && mIsAll) Log.d(TAG, "onStart: ----------------end");
    }


    /**
     * 并不属于生命周期
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (mIsLog) Log.w(TAG, "onRestoreInstanceState: ----------------begin1");
        super.onRestoreInstanceState(savedInstanceState);
        if (mIsLog && mIsAll) Log.w(TAG, "onRestoreInstanceState: ----------------end1");
    }

    /**
     * TODO 未调用
     */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        if (mIsLog) Log.w(TAG, "onRestoreInstanceState: ----------------begin2");
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        if (mIsLog && mIsAll) Log.w(TAG, "onRestoreInstanceState: ----------------end2");
    }

    @Override
    protected void onResume() {
        if (mIsLog) Log.d(TAG, "onResume: ----------------begin");
        super.onResume();
        if (mIsLog && mIsAll) Log.d(TAG, "onResume: ----------------end");
    }

    @Override
    protected void onPause() {
        if (mIsLog) Log.d(TAG, "onPause: ----------------begin");
        super.onPause();
        if (mIsLog && mIsAll) Log.d(TAG, "onPause: ----------------end");
    }

    /**
     * 1，super保存：View树状态
     * 2，未经自己允许，存在被杀死的可能性时调用，主动finish不调用
     *      A-->B：入栈不在最上层
     *      home：
     *      屏幕方向：
     *      配置修改、语言设置、设置不保留活动...
     * 3，调用onSave并不一定会调用onRestore
     * 4，在onStop之前，onPause不确定
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mIsLog) Log.w(TAG, "onSaveInstanceState: ----------------begin1");
        super.onSaveInstanceState(outState);
        if (mIsLog && mIsAll) Log.w(TAG, "onSaveInstanceState: ----------------end1");
    }

    /**
     * TODO 未调用
     */
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        if (mIsLog) Log.w(TAG, "onSaveInstanceState: ----------------begin2");
        super.onSaveInstanceState(outState, outPersistentState);
        if (mIsLog && mIsAll) Log.w(TAG, "onSaveInstanceState: ----------------end2");
    }

    @Override
    protected void onStop() {
        if (mIsLog) Log.d(TAG, "onStop: ----------------begin");
        super.onStop();
        if (mIsLog && mIsAll) Log.d(TAG, "onStop: ----------------end");
    }

    @Override
    protected void onDestroy() {
        if (mIsLog) Log.d(TAG, "onDestroy: ----------------begin");
        super.onDestroy();
        if (mIsLog && mIsAll) Log.d(TAG, "onDestroy: ----------------end");
    }
}
