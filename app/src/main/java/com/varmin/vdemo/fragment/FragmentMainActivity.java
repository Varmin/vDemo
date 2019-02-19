package com.varmin.vdemo.fragment;


import android.view.View;
import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;
import com.varmin.vdemo.base.Utils;

import butterknife.OnClick;

public class FragmentMainActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment_main;
    }

    @Override
    protected void initData() {
        super.initData();
    }


    @OnClick({R.id.add_remove, R.id.adapter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_remove:
                Utils.startActivity(this, ManaFragmentActivity.class);
                break;
            case R.id.adapter:
                break;
        }
    }
}
