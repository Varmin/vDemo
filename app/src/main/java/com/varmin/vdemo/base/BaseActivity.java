package com.varmin.vdemo.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
        if (getLayoutId() != 0) setContentView(getLayoutId());
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
        getFragStatus(isDelay, false);
    }
    public void getFragStatus(boolean isDelay, final boolean isMoreInfo){
        if (isDelay) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.e(TAG, "run: isDely");
                    getFragmentList(isMoreInfo);
                    getFragmentBackStackList();
                }
            }, 500);
        }else {
            getFragmentList(isMoreInfo);
            getFragmentBackStackList();
        }
    }
    public String getFragmentList() {return getFragmentList(false);}
    public String getFragmentList(boolean isMoreInfo) {
        StringBuilder builder = new StringBuilder();
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        builder.append("\nfrag_List: "+fragments.size());
        for (Fragment fragment : fragments) {
            builder.append("\n").append(fragment.getTag()+", id="+fragment.getId());
            if (isMoreInfo) {
                builder.append(", isAdd="+fragment.isAdded()+", isHiden="+fragment.isHidden()+
                        ", isDetached="+fragment.isDetached()+", hasCode="+fragment.hashCode());
            }
        }
        Log.d(TAG, "getFragmentList: "+builder.toString());
        return builder.toString();
    }
    public String getFragmentBackStackList() {
        StringBuilder builder = new StringBuilder();
        int bsCount = getSupportFragmentManager().getBackStackEntryCount();
        builder.append("\nstack_List: "+bsCount);
        for (int i = 0; i < bsCount; i++) {
            FragmentManager.BackStackEntry entry = getSupportFragmentManager().getBackStackEntryAt(i);
            builder.append("\n").append(entry.getName()+", id="+entry.getId());
        }
        Log.d(TAG, "getFragmentBackStackList: "+builder.toString());
        return builder.toString();
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<---Fragment信息---end---<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    public void hideSoft(View view){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
