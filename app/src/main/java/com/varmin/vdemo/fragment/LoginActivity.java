package com.varmin.vdemo.fragment;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 返回事件处理（返回键、View返回按钮）、Fragment打开另一个、过渡动画、Fragment传参、输入框焦点（抢返回事件）
 * 登录-手机号-验证码-设置密码、昵称信息（返回回到手机号界面）- 登录界面自动填写昵称、手机号和密码
 * <p>
 * 1. 填写手机号、验证码：返回到上一个页面； 设置密码：返回到发送验证码页面
 * 2.
 */
public class LoginActivity extends BaseActivity implements Fragment_2.JumpTypeListener {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_nick_name)
    EditText etNickName;
    @BindView(R.id.et_input)
    EditText etPassword;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.fl_container)
    FrameLayout flContainer;


    //是否完成注册
    private boolean isDone = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        super.initData();
        changeViewStatus();
    }

    private void changeViewStatus() {
        int visible = isDone ? View.VISIBLE : View.GONE;
        etNickName.setVisibility(visible);
        etPassword.setVisibility(visible);

        if (isDone) {
            tvTitle.setText("完成注册！");
            btnNext.setText("登录");
        } else {
            tvTitle.setText("请完成注册");
            btnNext.setText("注册");
        }
    }

    @OnClick(R.id.btn_next)
    public void onViewClicked() {
        if (isDone) {
            showToast("登录成功");
        } else {
            goPhone();
        }
    }

    //输入手机号界面
    private void goPhone() {
        flContainer.setVisibility(View.VISIBLE);
        tvTitle.setText("输入手机号");
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim, R.animator.pop_enter, R.animator.pop_exit)
                .add(R.id.fl_container, Fragment_2.newInstance(Fragment_2.TYPE_PHONE, ""), Fragment_2.TYPE_PHONE)
                .addToBackStack("back_stack_"+ Fragment_2.TYPE_PHONE)
                .commit();
        getFragStatus();
    }


    @Override
    public void jump(String type, String args) {
        switch (type) {
            case Fragment_2.TYPE_SECURITY:
                tvTitle.setText("输入验证码");
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim, R.animator.pop_enter, R.animator.pop_exit)
                        .add(R.id.fl_container, Fragment_2.newInstance(type, args), type)
                        .addToBackStack("back_stack_"+type)
                        .commit();
                break;
            case Fragment_2.TYPE_PASSWORD:
                tvTitle.setText("输入密码、昵称");
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim, R.animator.pop_enter, R.animator.pop_exit)
                        .add(R.id.fl_container, Fragment_2.newInstance(type, args), type)
                        .addToBackStack("back_stack_"+type)
                        .commit();
                break;
            case Fragment_2.TYPE_DONE:
                for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); i++) {
                    getSupportFragmentManager().popBackStack();
                }
                flContainer.setVisibility(View.GONE);
                String[] ags = args.split("_");
                isDone = true;
                changeViewStatus();
                etNickName.setText(ags[0]);
                etPassword.setText(ags[1]);
                break;
        }
        getFragStatus();
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: ");

        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments.size() > 0) {
            Fragment fg = fragments.get(fragments.size() - 1);
            if (fg instanceof Fragment_2) {
                Log.d(TAG, "onBackPressed: "+fg.getTag());
                if (((Fragment_2) fg).onBackPressed()) {
                    getFragStatus();
                    return;
                }
            }
        }
        getFragStatus();
        super.onBackPressed();
    }
}
