package com.varmin.vdemo.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseLifeFragment;
import butterknife.BindView;
import butterknife.Unbinder;

public class Fragment_2 extends BaseLifeFragment {

    @BindView(R.id.tv_fg_1)
    TextView tvFg1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_2;
    }

    @Override
    public void initView(View mView) {
        String args = null;
        Bundle bundle = getArguments();
        if (bundle != null) {
            args = getArguments().getString("args");
        }
        if (TextUtils.isEmpty(args)) {
            args = getTag();
        }
        tvFg1.setText(args);
    }

    public static Fragment_1 newInstance() {
        return newInstance("Fragment_2_Tag");
    }

    public static Fragment_1 newInstance(String args) {
        Fragment_1 fragment = new Fragment_1();
        Bundle bundle = new Bundle();
        bundle.putString("args", args);
        fragment.setArguments(bundle);
        return fragment;
    }



}
