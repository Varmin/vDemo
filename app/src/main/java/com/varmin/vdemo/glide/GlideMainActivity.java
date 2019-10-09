package com.varmin.vdemo.glide;

import android.view.View;

import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;
import com.varmin.vdemo.base.Utils;

import butterknife.OnClick;

public class GlideMainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_glide_main;
    }

    @OnClick({R.id.btn_glide_1})
    public void onViewClicked(View view){
        switch (view.getId()) {
            case R.id.btn_glide_1:
                Utils.startActivity(GlideBaseActivity.class);
                break;
        }
    }
}
