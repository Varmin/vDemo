package com.varmin.vdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseFragment;
import com.varmin.vdemo.base.BaseLifeFragment;

import butterknife.BindView;

public class StateFragment_1 extends BaseFragment {
    @BindView(R.id.et_frag_info)
    EditText editText;

    public StateFragment_1(){
        Log.d(TAG, "StateFragment_1: constructor");
    }

    @Override
    public void parseArguments() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_state_fragment_1;
    }

    @Override
    public void initView(View mView) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }
}
