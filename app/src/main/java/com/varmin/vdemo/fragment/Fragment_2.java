package com.varmin.vdemo.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseLifeFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class Fragment_2 extends BaseLifeFragment {
    public static final String TYPE_PHONE = "type_phone";
    public static final String TYPE_SECURITY = "type_security";
    public static final String TYPE_PASSWORD = "type_password";
    public static final String TYPE_DONE = "type_done";
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    @BindView(R.id.btn_finish)
    Button btnFinish;
    @BindView(R.id.et_nick_name)
    EditText etNickName;
    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.btn_next)
    Button btnNext;
    private String args;
    private String type;
    private JumpTypeListener jumpListener;

    public static Fragment_2 newInstance(String type, String args) {
        Fragment_2 fragment = new Fragment_2();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putString("args", args);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void parseArguments() {
        Bundle bundle = getArguments();
        args = bundle.getString("args");
        type = bundle.getString("type");
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_2;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        TAG += ("_"+getTag());
        jumpListener = (JumpTypeListener) context;
    }


    @Override
    public void initView(View mView) {

        if (!TextUtils.isEmpty(type)) {

            switch (type) {
                case TYPE_PHONE:
                    etInput.setText("10022000000");
                    btnNext.setText("获取验证码");
                    break;
                case TYPE_SECURITY:
                    etNickName.setText(args);
                    etNickName.setEnabled(false);
                    etInput.setText("666666");
                    btnNext.setText("设置");
                    break;
                case TYPE_PASSWORD:
                    etNickName.setVisibility(View.VISIBLE);
                    etNickName.setText("Varmin");
                    etInput.setText("123456");
                    btnNext.setText("完成");
                    break;
            }
        }
    }


    @OnClick({R.id.btn_finish, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_finish:
                getMActivity().onBackPressed();
                break;
            case R.id.btn_next:
                //可以在Fragment中跳转，也可以统一接口在Activity中跳转
                switch (type) {
                    case TYPE_PHONE:
                         /*getActivity().getSupportFragmentManager().beginTransaction()
                                 .add(R.id.fl_container, Fragment_2.newInstance(TYPE_SECURITY, etInput.getText().toString()))
                                 .addToBackStack()*/
                         jumpListener.jump(TYPE_SECURITY, etInput.getText().toString());
                        break;
                    case TYPE_SECURITY:
                         jumpListener.jump(TYPE_PASSWORD, "");
                        break;
                    case TYPE_PASSWORD:
                        jumpListener.jump(TYPE_DONE, etNickName.getText().toString()+"_"+etInput.getText().toString());
                        break;
                }
                break;
        }
    }


    /**
     * Fragment监听返回键
     */
    public boolean onBackPressed(){
        Log.d(TAG, "onBackPressed: "+type);
        switch (type) {
            case TYPE_PHONE:
                //只通知了Fragment，未拦截Activity的返回处理
                getMActivity().findViewById(R.id.fl_container).setVisibility(View.GONE);
                ((TextView)getMActivity().findViewById(R.id.tv_title)).setText("请完成注册啊喂");
                break;
            case TYPE_SECURITY:
                break;
            case TYPE_PASSWORD:
                //通知了Fragment，并拦截了Activity的处理
                getMActivity().getSupportFragmentManager()
                        .popBackStack("back_stack_"+TYPE_PHONE, 0);
                return true;
        }
        return false;
    }


    public interface JumpTypeListener {
        void jump(String type, String args);
    }

}
