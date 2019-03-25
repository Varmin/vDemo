package com.varmin.vdemo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
 * Created by HuangYang
 * on 2019/2/27  11:07 AM.
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
                        //fragment1 = (Fragment_1) getSupportFragmentManager().findFragmentByTag("tag_"+Fragment_1.class.getSimpleName());
                        if (fragment1 == null) {
                            fragment1 = Fragment_1.newInstance();
                            Log.e(TAG, "onCheckedChanged: new-----------1");
                        }
                        fragment = fragment1;
                        break;
                    case R.id.rb_cover_2:
                        //fragment2 = (StateFragment_1)getSupportFragmentManager().findFragmentByTag("tag_"+StateFragment_1.class.getSimpleName());
                        if (fragment2 == null) {
                            fragment2 = new StateFragment_1();
                            Log.e(TAG, "onCheckedChanged: new -----------State_1");
                        }
                        fragment = fragment2;
                        break;
                    case R.id.rb_cover_3:
                        //fragment3 = (StateFragment_2)getSupportFragmentManager().findFragmentByTag("tag_"+StateFragment_2.class.getSimpleName());
                        if (fragment3 == null) {
                            fragment3 = new StateFragment_2();
                            Log.e(TAG, "onCheckedChanged: new -----------State_2");
                        }
                        fragment = fragment3;
                        break;
                    case R.id.rb_cover_4:
                        //fragment4 = (StateFragment_3)getSupportFragmentManager().findFragmentByTag("tag_"+StateFragment_3.class.getSimpleName());
                        if (fragment4 == null) {
                            fragment4 = new StateFragment_3();
                            Log.e(TAG, "onCheckedChanged: new -----------State_3");
                        }
                        fragment = fragment4;
                        break;
                }
                Log.d(TAG, "onCheckedChanged: "+fragment.getClass().getSimpleName()+", iaAdd="+fragment.isAdded()+", isHiden="+fragment.isHidden());
                FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
                if (!fragment.isAdded()) {
                    trans.add(R.id.fl_conver_container, fragment, "tag_"+fragment.getClass().getSimpleName());
                    //trans.addToBackStack("stack_"+fragment.getClass().getSimpleName());
                }
                if (fragment.isHidden()) {
                    trans.show(fragment);
                }
                trans.commitNow();


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
                getFragStatus(false, true);
            }
        });
    }
}
