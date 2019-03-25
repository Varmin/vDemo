package com.varmin.vdemo.activitys;

import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;
import com.varmin.vdemo.base.Utils;

import butterknife.OnClick;

public class LifeActivity_1 extends BaseActivity {
    {
        isAll(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_life_1;
    }


    @OnClick(R.id.btn_life_1)
    public void onViewClicked() {
        Utils.startActivity(LifeActivity_2.class);
    }
}
