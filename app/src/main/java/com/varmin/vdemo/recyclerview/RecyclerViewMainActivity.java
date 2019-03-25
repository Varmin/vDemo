package com.varmin.vdemo.recyclerview;

import android.view.View;
import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;
import com.varmin.vdemo.base.Utils;

import butterknife.OnClick;

/**
 *
 */
public class RecyclerViewMainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycler_view_main;
    }


    @OnClick({R.id.btn_recycler_no_pack, R.id.btn_recycler_pack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_recycler_no_pack:
                Utils.startActivity(UnPackActivity.class);
                break;
            case R.id.btn_recycler_pack:
                break;
        }
    }
}
