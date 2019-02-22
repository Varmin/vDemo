package com.varmin.vdemo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;
import com.varmin.vdemo.base.Utils;
import com.varmin.vdemo.test.TestActivity;

import junit.framework.Test;

import butterknife.OnClick;

public class FragmentMainActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment_main;
    }


    @OnClick({R.id.add_remove, R.id.adapter, R.id.state})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_remove:
                Utils.startActivity(this, ManaFragmentActivity.class);
                break;
            case R.id.adapter:
                Utils.startActivity(this, LoginActivity.class);
                break;
            case R.id.state:
                Intent intent = new Intent(mActivity, FragmentStateActivity.class);
                intent.putExtra("value", "varmin");
                startActivity(intent);
                //Utils.startActivity(mActivity, TestActivity.class);
                break;
        }
    }
}
