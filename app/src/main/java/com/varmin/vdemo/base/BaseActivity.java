package com.varmin.vdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by HuangYang
 * on 2018/10/26  16:23.
 * 文件描述：
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        mUnBinder = ButterKnife.bind(this);
        initData();
    }

    protected void initData() {

    }

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) mUnBinder.unbind();
    }

    protected AppCompatActivity getActivity(){
        return this;
    }
}
