package com.varmin.vdemo.henCoder;


import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;
import com.varmin.vdemo.view.FlowLabelLayout;

import java.util.Arrays;

import butterknife.BindView;

public class FlowLayoutActivity extends BaseActivity {
    @BindView(R.id.flow_layout)
    FlowLabelLayout flowLayout;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_label_layout;
    }

    @Override
    protected void initData() {
        super.initData();
        String[] tags = new String[]{"北京", "天津", "哈尔滨", "齐齐哈尔","河南省郑州市", "西藏", "我的", "牛逼","嘿哈", "tag", "123", "*！@#￥%……&","《》「」【】"};
//        flowLayout.setTags(Arrays.asList(tags));
    }
}
