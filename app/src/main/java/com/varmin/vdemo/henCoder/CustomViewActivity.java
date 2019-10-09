package com.varmin.vdemo.henCoder;


import android.widget.Toast;

import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;
import com.varmin.vdemo.view.FlipView;
import com.varmin.vdemo.view.LabelLayout;
import com.varmin.vdemo.view.MaterialEditText;

import butterknife.BindView;

public class CustomViewActivity extends BaseActivity {
    @BindView(R.id.view_flip)
    FlipView flipView;
    @BindView(R.id.met_edit)
    MaterialEditText materialEditText;
    @BindView(R.id.ll_label_layout)
    LabelLayout labelLayout;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_camera_view;
    }

    @Override
    protected void initData() {
        super.initData();

        String[] labels = new String[]{"标签标签标签标签标签标签","标签","标签","标签","标签","标签","标签","标签","标签","标签","标签"
                ,"标签","标签","标签","标签","标签","标签","标签","标签","标签","标签","标签"};
        for (int i = 0; i < labels.length; i++) {
            labels[i] += (i);
        }
        labelLayout.setLabels(labels);
        labelLayout.setOnLabelClickListener(new LabelLayout.OnLabelClickListener() {
            @Override
            public void labelClick(String label) {
                Toast.makeText(getActivity(), label, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
