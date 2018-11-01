package com.varmin.vdemo.henCoder;

import android.view.View;
import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;
import com.varmin.vdemo.base.Utils;

import butterknife.OnClick;

public class HenCoderMainActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }

    @OnClick({R.id.camera_view})
    public void onViewClick(View view){
        switch (view.getId()) {
            case R.id.camera_view:
                Utils.startActivity(this, CustomViewActivity.class);
                break;
        }


    }
}
