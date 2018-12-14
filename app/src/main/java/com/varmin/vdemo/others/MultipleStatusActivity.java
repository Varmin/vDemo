package com.varmin.vdemo.others;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.varmin.multiplestatusview.MultipleStatusView;
import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;

import butterknife.BindView;

public class MultipleStatusActivity extends BaseActivity {
    private static final String TAG = "MultipleStatusActivity";
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.msv_view)
    MultipleStatusView msvView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_multiple_satus;
    }

    @Override
    protected void initData() {
        super.initData();

        Log.d(TAG, "initData: "+msvView.getChildCount());

        //todo 为什么xml里不能直接写提示？为什么build要exclude？
        tvContent.postDelayed(new Runnable() {
            @Override
            public void run() {
                msvView.showLoading();
            }
        }, 1000);

        msvView.postDelayed(new Runnable() {
            @Override
            public void run() {
                msvView.showSuccess();
            }
        }, 3000);

        tvContent.postDelayed(new Runnable() {
            @Override
            public void run() {
                msvView.showContentView();
            }
        }, 5000);
    }
}
