package com.varmin.vdemo.fragment;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;
import com.varmin.vdemo.base.Utils;
import com.varmin.vdemo.test.TestActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class FragmentStateActivity extends BaseActivity {

    @BindView(R.id.fl_state_container)
    FrameLayout flStateContainer;
    @BindView(R.id.tv_state_info)
    TextView tvStateInfo;
    private Fragment fragSta2;
    private String value;
    private String value2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment_state;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        value = getIntent().getStringExtra("value");
        value2 = "initData";
        setStateInfo("onCreate");

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_state_container, StateFragment_1.instantiate(mActivity, StateFragment_1.class.getName()),"state_add_1")
                .commit();
        fragSta2 = StateFragment_2.instantiate(mActivity, StateFragment_2.class.getName());
    }

    private void setStateInfo(String tag){
        StringBuilder builder = new StringBuilder();
        builder.append("tag="+tag)
                .append(", value="+value)
                .append(", value2="+value2);
        Log.d(TAG, "setStateInfo: "+builder.toString());
        tvStateInfo.setText(builder.toString());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getFragStatus(false);
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        getFragStatus(false);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        getFragStatus(false);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        getFragStatus(false);
    }


    //----------------------------------------------------------------------------

    @OnClick({R.id.btn_state_show, R.id.btn_state_hide, R.id.btn_state_replace, R.id.btn_state_go})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_state_show:
                FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
                if (!fragSta2.isAdded()) {
                    trans.add(R.id.fl_state_container, fragSta2, "state_show_1");
                }
                if (fragSta2.isHidden()) {
                    trans.show(fragSta2);
                }
                trans.addToBackStack("stack_show_2").commit();
                break;
            case R.id.btn_state_hide:
                FragmentTransaction tran = getSupportFragmentManager().beginTransaction();
                if (!fragSta2.isHidden()) {
                    tran.hide(fragSta2);
                }
                tran.addToBackStack("stack_hide_2").commit();
                break;
            case R.id.btn_state_replace:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_state_container, StateFragment_3.instantiate(mActivity, StateFragment_3.class.getName()), "state_repalce_3")
                        .addToBackStack("stack_replace_3")
                        .commit();
                break;
            case R.id.btn_state_go:
                Utils.startActivity(mActivity, TestActivity.class);
                break;
        }
        getFragStatus();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getFragStatus();
    }
}
