package com.varmin.vdemo;

import android.view.View;

import com.ptbaby.applypermissions.PermissionUtils;
import com.varmin.vdemo.activitys.ActivityMainActivity;
import com.varmin.vdemo.base.BaseActivity;
import com.varmin.vdemo.base.Utils;
import com.varmin.vdemo.fragment.FragmentMainActivity;
import com.varmin.vdemo.henCoder.HenCoderMainActivity;
import com.varmin.vdemo.others.LoadBigBitmapActivity;
import com.varmin.vdemo.recyclerview.RecyclerViewMainActivity;
import com.varmin.vdemo.recyclerview.UnPackActivity;
import com.varmin.vdemo.test.TestActivity;

import butterknife.OnClick;


public class MainActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        super.initData();
        Utils.startActivity(UnPackActivity.class);
    }

    @OnClick({R.id.btn_main_1, R.id.btn_main_2, R.id.btn_main_3, R.id.btn_main_4, R.id.btn_main_5, R.id.btn_main_6})
    public void onViewClicked(View view){
        switch (view.getId()) {
            case R.id.btn_main_1:
                Utils.startActivity(this,HenCoderMainActivity.class);
                break;
            case R.id.btn_main_2:
                Utils.startActivity(this,LoadBigBitmapActivity.class);
                break;
            case R.id.btn_main_3:
                Utils.startActivity(FragmentMainActivity.class);
                break;
            case R.id.btn_main_4:
                Utils.startActivity(RecyclerViewMainActivity.class);
                break;
            case R.id.btn_main_5:
                Utils.startActivity(ActivityMainActivity.class);
                break;
            case R.id.btn_main_6:
                Utils.startActivity(RecyclerViewMainActivity.class);
                break;
        }

    }

}

