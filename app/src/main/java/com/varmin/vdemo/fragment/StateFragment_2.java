package com.varmin.vdemo.fragment;

import android.util.Log;
import android.view.View;

import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseFragment;
import com.varmin.vdemo.base.BaseLifeFragment;

public class StateFragment_2 extends BaseFragment {
    
    public StateFragment_2(){
        Log.d(TAG, "StateFragment_2: constructor");
    }

    @Override
    public void parseArguments() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_state_fragment_2;
    }

    @Override
    public void initView(View mView) {

    }
}
