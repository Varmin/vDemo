package com.varmin.vdemo.henCoder;


import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;
import com.varmin.vdemo.view.FlipView;
import com.varmin.vdemo.view.MaterialEditText;

import butterknife.BindView;

public class CameraViewActivity extends BaseActivity {
    @BindView(R.id.view_flip)
    FlipView flipView;
    @BindView(R.id.met_edit)
    MaterialEditText materialEditText;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_camera_view;
    }

    @Override
    protected void initData() {
        super.initData();

    }
}
