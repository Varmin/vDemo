package com.varmin.vdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ToggleButton;

import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by HuangYang
 * on 2019/2/26  7:20 PM.
 * 文件描述：Fragment销毁重建。
 *
 */
public class CoverShowHidenActivity extends BaseActivity {
    @BindView(R.id.tb_toogle)
    ToggleButton toggleButton;
    CoverAddHidenFragment fragment1;
    CoverAddHidenFragment fragment2;
    Fragment curFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cover_show_hiden;
    }

    @OnClick({R.id.btn_cover_show, R.id.btn_cover_show2})
    public void onViewClicked(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.btn_cover_show:
                if (fragment1 == null) fragment1 = CoverAddHidenFragment.getInstance(0);
                fragment = fragment1;
                break;
            case R.id.btn_cover_show2:
                if (fragment2 == null) fragment2 = CoverAddHidenFragment.getInstance(1);
                fragment = fragment2;
                break;
        }
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()) trans.add(R.id.fl_cover_container, fragment, "add_" + fragment.hashCode());
        if (fragment.isHidden()) trans.show(fragment);
        if (curFragment != null && curFragment != fragment) trans.hide(curFragment);
        trans.commit();

        curFragment = fragment;
        getFragStatus(true, true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getFragStatus(false, true);
        if (toggleButton.isChecked()) {
            if (fragment1 != null) outState.putString("frag1_hash", fragment1.getTag());
            if (fragment2 != null) outState.putString("frag2_hash", fragment2.getTag());
            if (curFragment != null) outState.putString("curr_bash", curFragment.getTag());
            Log.d(TAG, "onSaveInstanceState: " + fragment1.getTag() + ", " + fragment2.getTag() + ", " + curFragment.getTag());
        }
    }

    /**
     * 尽量把Fragment的恢复放在onRestore中，不打乱生命周期中的逻辑。
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        getFragStatus(false, true);
        if (toggleButton.isChecked()) {
            String frag1_code = savedInstanceState.getString("frag1_hash");
            String frag2_code = savedInstanceState.getString("frag2_hash");
            String curCode = savedInstanceState.getString("curr_bash");
            Log.d(TAG, "onRestoreInstanceState: " + frag1_code + ", " + frag2_code + ", " + curCode);
            /**
             * onCreate中新生成的fragment还对应着原来的tag，可以找到。
             */
            fragment1 = (CoverAddHidenFragment) getSupportFragmentManager().findFragmentByTag(frag1_code);
            fragment2 = (CoverAddHidenFragment) getSupportFragmentManager().findFragmentByTag(frag2_code);
            curFragment = getSupportFragmentManager().findFragmentByTag(curCode);
            Log.d(TAG, "onRestoreInstanceState: "+fragment1+", "+fragment2+", "+curFragment);
        }
    }
}
