package com.varmin.vdemo.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by HuangYang
 * on 2018/10/26  16:23.
 * 文件描述：
 */

public abstract class BaseActivity extends BaseLifeActivity {

    private Unbinder mUnBinder;
    public AppCompatActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnBinder = ButterKnife.bind(this);
        mActivity = this;
        initData();
        initData(savedInstanceState);
    }

    protected void initData(@Nullable Bundle savedInstanceState) { }

    protected void initData() {}

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) mUnBinder.unbind();
    }

    protected AppCompatActivity getActivity(){
        return this;
    }

    public void showToast(String toast){
        Toast.makeText(mActivity, toast, Toast.LENGTH_SHORT).show();
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>---Fragment信息---begin--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public void getFragStatus(){
        getFragStatus(true);
    }
    public void getFragStatus(boolean isDelay){
        if (isDelay) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getFragmentList();
                    getFragmentBackStackList();
                }
            }, 500);
        }else {
            getFragmentList();
            getFragmentBackStackList();
        }
    }
    public void getFragmentList() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        Log.d(TAG, "getFragList: " + fragments.size());
        for (Fragment fragment : fragments) {
            Log.d(TAG, "getFragList: " + fragment.getTag());
        }
    }
    public void getFragmentBackStackList() {
        int bsCount = getSupportFragmentManager().getBackStackEntryCount();
        Log.d(TAG, "getBackStackList: size="+bsCount);
        for (int i = 0; i < bsCount; i++) {
            FragmentManager.BackStackEntry entry = getSupportFragmentManager().getBackStackEntryAt(i);
            Log.d(TAG, "getBackStackList: "+entry.getName()+", id="+entry.getId());
        }
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<---Fragment信息---end---<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
}
