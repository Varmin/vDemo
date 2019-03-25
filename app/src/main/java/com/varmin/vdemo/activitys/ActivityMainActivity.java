package com.varmin.vdemo.activitys;

import android.view.View;
import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;
import com.varmin.vdemo.base.Utils;
import butterknife.OnClick;

public class ActivityMainActivity extends BaseActivity {
    {
        isAll(true);
        setTAG("LifeActivity_Main");
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_activites;
    }


    @OnClick({R.id.btn_act_life, R.id.btn_act_mode, R.id.btn_act_filter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_act_life:
                Utils.startActivity(LifeActivity_1.class);
                break;
            case R.id.btn_act_mode:
                break;
            case R.id.btn_act_filter:
                break;
        }
    }
}
