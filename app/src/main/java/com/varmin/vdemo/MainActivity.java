package com.varmin.vdemo;

import android.view.View;
import com.varmin.vdemo.activitys.ActivityMainActivity;
import com.varmin.vdemo.base.BaseActivity;
import com.varmin.vdemo.base.Utils;
import com.varmin.vdemo.fragment.FragmentMainActivity;
import com.varmin.vdemo.glide.GlideBaseActivity;
import com.varmin.vdemo.glide.GlideMainActivity;
import com.varmin.vdemo.henCoder.CustomViewActivity;
import com.varmin.vdemo.henCoder.FlowLayoutActivity;
import com.varmin.vdemo.henCoder.HenCoderMainActivity;
import com.varmin.vdemo.net.OkHttpTestActivity;
import com.varmin.vdemo.others.LoadBigBitmapActivity;
import com.varmin.vdemo.recyclerview.RecyclerViewMainActivity;
import com.varmin.vdemo.rx.RxJavaMainActivity;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        super.initData();
        switch (2){
            case 0:
                Utils.startActivity(RxJavaMainActivity.class);
                break;
            case 1:
                Utils.startActivity(RecyclerViewMainActivity.class);
                break;
            case 2:
                Utils.startActivity(FlowLayoutActivity.class);
                break;
        }
    }

    @OnClick({R.id.btn_main_1, R.id.btn_main_2, R.id.btn_main_3
            , R.id.btn_main_4, R.id.btn_main_5, R.id.btn_main_6
            , R.id.btn_main_7})
    public void onViewClicked(View view){
        switch (view.getId()) {
            case R.id.btn_main_1:
                Utils.startActivity(this,HenCoderMainActivity.class);
//                Utils.startActivity(JiKePraiseActivity.class);
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
            case R.id.btn_main_7:
                Utils.startActivity(OkHttpTestActivity.class);
                break;
        }

    }

}

