package com.varmin.vdemo.base;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public abstract class BaseFragment extends BaseLifeFragment {

    public BaseFragment(){
        Log.w(TAG, "BaseFragment: constructor="+getClass().getSimpleName()+", "+hashCode());
    }
    private BaseLifeViewModel mViewModel;
    //是否处理返回按键
    private boolean mBackHandle;
    public void setBackHandle(boolean isHandle){
        this.mBackHandle = isHandle;
    }
    public boolean getBackHandle(){return mBackHandle;}

    public void hideSoft(View view){
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
