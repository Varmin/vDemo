package com.varmin.vdemo.others;

import android.widget.TextView;

import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;
import butterknife.BindView;

public class MultipleStatusActivity extends BaseActivity {
    private static final String TAG = "MultipleStatusActivity";
    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_multiple_satus;
    }

    @Override
    protected void initData() {
        super.initData();
    }
}
