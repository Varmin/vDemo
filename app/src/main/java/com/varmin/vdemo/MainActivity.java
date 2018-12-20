package com.varmin.vdemo;

import android.view.View;

import com.varmin.permissionapply.Permissions;
import com.varmin.vdemo.base.BaseActivity;
import com.varmin.vdemo.base.Utils;
import com.varmin.vdemo.henCoder.HenCoderMainActivity;
import com.varmin.vdemo.others.LoadBigBitmapActivity;

import butterknife.OnClick;


public class MainActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.btn_main_1, R.id.btn_main_2})
    public void onViewClicked(View view){
        switch (view.getId()) {
            case R.id.btn_main_1:
                Utils.startActivity(this,HenCoderMainActivity.class);
                break;
            case R.id.btn_main_2:
                Utils.startActivity(this,LoadBigBitmapActivity.class);
                break;
        }

    }

}

