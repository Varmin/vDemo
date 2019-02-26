package com.varmin.vdemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseFragment;
import com.varmin.vdemo.base.BaseLifeFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class Fragment_1 extends BaseFragment {

    @BindView(R.id.tv_fg_1)
    TextView tvFg1;
    private TransArgumentsListener mTransArgusListener;
    private String args;

    @Override
    public void parseArguments() {
        args = null;
        Bundle bundle = getArguments();
        if (bundle != null) {
            args = getArguments().getString("args");
        }
        if (TextUtils.isEmpty(args)) {
            args = getTag();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_1;
    }

    @Override
    public void initView(View mView) {
        tvFg1.setText(args);
    }

    @OnClick({R.id.tv_fg_1})
    public void onViewClicked(View view) {
        if (mTransArgusListener != null) {
            mTransArgusListener.setArgus(tvFg1.getText().toString());
        }
    }

    public static Fragment_1 newInstance() {
        return newInstance("Fragment_1_Tag");
    }

    public static Fragment_1 newInstance(String args) {
        Fragment_1 fragment = new Fragment_1();
        Bundle bundle = new Bundle();
        bundle.putString("args", args);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Fragment传递参数给Activity
        try{
            this.mTransArgusListener = (TransArgumentsListener)context;
        }catch (Exception e){}
    }



    public void setText(String str){
        tvFg1.setText(str);
    }
}
