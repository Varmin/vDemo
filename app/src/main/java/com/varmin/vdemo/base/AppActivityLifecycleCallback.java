package com.varmin.vdemo.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuangYang
 * on 2019/3/4  11:36.
 * 文件描述：
 */
public class AppActivityLifecycleCallback implements Application.ActivityLifecycleCallbacks {
    List<Activity> mActivityList = new ArrayList<>();
    WeakReference<Activity> mTopActivityWeakRef;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        mActivityList.add(activity);
        setTopActivityWeakRef(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        setTopActivityWeakRef(activity);
    }

    @Override
    public void onActivityResumed(Activity activity) {
        setTopActivityWeakRef(activity);
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        mActivityList.remove(activity);
    }

    private void setTopActivityWeakRef(Activity activity){
        if (mTopActivityWeakRef == null || !activity.equals(mTopActivityWeakRef.get())) {
            mTopActivityWeakRef = new WeakReference<>(activity);
        }
    }

    public Activity getTopActivity(){
        Activity activity = null;
        if (mTopActivityWeakRef != null) {
            activity = mTopActivityWeakRef.get();
        }
        if (activity == null && mActivityList != null && mActivityList.size() != 0) {
            activity = mActivityList.get(mActivityList.size() -1);
        }
        return activity;
    }
}
