package com.varmin.vdemo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;
import com.varmin.vdemo.base.Utils;
import com.varmin.vdemo.test.TestActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 销毁重建
 */
public class FragmentStateActivity extends BaseActivity {

    @BindView(R.id.fl_state_container)
    FrameLayout flStateContainer;
    @BindView(R.id.tv_state_info)
    TextView tvStateInfo;
    @BindView(R.id.tbtn_save_toggle)
    ToggleButton tbSave;
    @BindView(R.id.et_edit_text)
    EditText editText;
    private Fragment fragSta2;
    private String value = "bundle_***";
    private String value2 = "onCreate_***";
    private String value3 = "global_value";
    private String value4 = "resume_***";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment_state;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        value = "bundle_"+getIntent().getStringExtra("value");
        value2 = "onCreate_value";

        if (savedInstanceState == null) {
            fragSta2 = StateFragment_2.instantiate(mActivity, StateFragment_2.class.getName());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fl_state_container, StateFragment_1.instantiate(mActivity, StateFragment_1.class.getName()),"state_add_1")
                    .commitNow();
        }else {
            boolean isToggle = savedInstanceState.getBoolean("toggle");
            tbSave.setChecked(isToggle);
            if (isToggle) {
                value4 = savedInstanceState.getString("value_4");
            }
            /**
             * 被add、replace等后，在fragments里已存在的fragment
             * 在onSave中会被保存，onRestore时会被自动重新实例化
             * 所以，要判断是否已经有了
             */
            fragSta2 = getSupportFragmentManager().findFragmentByTag("state_show_1");
            if (fragSta2 == null) {
                fragSta2 = StateFragment_2.instantiate(mActivity, StateFragment_2.class.getName());
            }
        }

        tbSave.setFocusable(true);
        setStateInfo("onCreate");
    }

    private void setStateInfo(final String tag){
        StringBuilder builder = new StringBuilder();
        builder.append("tag="+tag)
                .append("\n").append(" value="+value)
                .append("\n").append(" value2="+value2)
                .append("\n").append(" value3="+value3)
                .append("\n").append(" value4="+value4)
                .append("\n");
        builder.append("\n").append(getFragmentList());
        builder.append("\n").append(getFragmentBackStackList());
        Log.d(TAG, "\nsetStateInfo: "+builder.toString());
        tvStateInfo.setText(builder.toString());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
        setStateInfo("onSave");

        outState.putString("value_4", value4);
        outState.putBoolean("toggle", tbSave.isChecked());
    }


    @Override
    protected void onResume() {
        super.onResume();
        value4 = "resume_value";
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState: ");
        setStateInfo("onRestore");
    }


    //----------------------------------------------------------------------------

    @OnClick({R.id.btn_state_show, R.id.btn_state_hide, R.id.btn_state_replace, R.id.btn_state_go
    , R.id.tbtn_save_toggle})
    public void onViewClicked(View view){
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
            case R.id.tbtn_save_toggle:
                Log.d(TAG, "onClickedView: "+tbSave.isChecked()+", "+tbSave.isSelected()+", "+tbSave.isShown());
                break;
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setStateInfo("onClick");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setStateInfo("onBack");
    }

    @Override
    protected void onPause() {
        super.onPause();
        hideSoft(editText);
    }
}
