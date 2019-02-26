package com.varmin.vdemo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;
import com.varmin.vdemo.base.Utils;
import com.varmin.vdemo.test.TestActivity;

import java.util.List;

import butterknife.BindView;

/**
 * 重叠问题
 */
public class FragmentCoverActivity extends BaseActivity {

    @BindView(R.id.fl_conver_container)
    FrameLayout flConverContainer;
    @BindView(R.id.rg_cover)
    RadioGroup rgCover;
    private Fragment_1 fragment1;
    private StateFragment_1 fragment2;
    private StateFragment_2 fragment3;
    private StateFragment_3 fragment4;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment_cover;
    }


    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        findViewById(R.id.btn_cover_jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.startActivity(mActivity, TestActivity.class);
            }
        });


        initListener();
        ((RadioButton)rgCover.findViewById(R.id.rb_cover_1)).setChecked(true);
    }

    private void initListener() {
        rgCover.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Fragment fragment = null;
                switch (checkedId) {
                    case R.id.rb_cover_1:
                        if (fragment1 == null) {
                            fragment1 = Fragment_1.newInstance();
                        }
                        fragment = fragment1;
                        break;
                    case R.id.rb_cover_2:
                        if (fragment2 == null) {
                            fragment2 = new StateFragment_1();
                        }
                        fragment = fragment2;
                        break;
                    case R.id.rb_cover_3:
                        if (fragment3 == null) {
                            fragment3 = new StateFragment_2();
                        }
                        fragment = fragment3;
                        break;
                    case R.id.rb_cover_4:
                        if (fragment4 == null) {
                            fragment4 = new StateFragment_3();
                        }
                        fragment = fragment4;
                        break;
                }
                Log.d(TAG, "onCheckedChanged: "+fragment.isAdded()+", "+fragment.isHidden());
                FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
                if (!fragment.isAdded()) {
                    trans.add(R.id.fl_conver_container, fragment, "tag_"+fragment.getClass().getSimpleName());
                    trans.addToBackStack("stack_"+fragment.getClass().getSimpleName());
                }
                if (fragment.isHidden()) {
                    trans.show(fragment);
                }
                trans.commit();

                List<Fragment> frgs = getSupportFragmentManager().getFragments();
                for (Fragment frg : frgs) {
                    if (frg != fragment) {
                        if (!frg.isHidden()) {
                            Log.d(TAG, "onCheckedChanged: hide="+frg.getClass().getSimpleName());
                            getSupportFragmentManager().beginTransaction().hide(frg).commit();
                        }else {
                            Log.d(TAG, "onCheckedChanged: has hide="+frg.getClass().getSimpleName());
                        }
                    }else {
                        Log.d(TAG, "onCheckedChanged: no hide="+frg.getClass().getSimpleName());
                    }
                }
                //trans.commitAllowingStateLoss();
                getFragStatus(true, true);
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e(TAG, "onRestoreInstanceState: ----------");
        getFragStatus(false, true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG, "onSaveInstanceState: -------------");
        getFragStatus(false, true);
    }

}
