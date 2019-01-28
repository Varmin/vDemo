package com.varmin.vdemo.others;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;

import butterknife.BindView;

public class _RenameActivity extends BaseActivity {
    @BindView(R.id.btn_get)
    TextView tvGet;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sp2;
    }

    @Override
    protected void initData() {
        super.initData();
        SharedPreferences sp = getSharedPreferences("varmin", Context.MODE_PRIVATE);
    }
}
