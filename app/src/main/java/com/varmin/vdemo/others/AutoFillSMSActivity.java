package com.varmin.vdemo.others;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Telephony;
import android.view.View;
import android.widget.TextView;

import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tech.michaelx.authcode.AuthCode;
import tech.michaelx.authcode.CodeConfig;

public class AutoFillSMSActivity extends BaseActivity {

    @BindView(R.id.tv_code)
    TextView tvCode;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == SMSContentObserver.RECEIVER_SMS_CODE_MSG) {
                String smsCode = (String) msg.obj;
                tvCode.setText(smsCode);
            }
        }
    };
    private SMSContentObserver smsObserver;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_auto_fill_sms;
    }

    @Override
    protected void initData() {
        super.initData();
        smsObserver = new SMSContentObserver(mActivity, mHandler);
        getContentResolver().registerContentObserver(
                Uri.parse(SMSContentObserver.SMS_URI), true, smsObserver);


        /*CodeConfig config = new CodeConfig.Builder()
                .codeLength(6) // 设置验证码长度
                //.smsFromStart(133) // 设置验证码发送号码前几位数字
                //.smsFrom(1690123456789) // 如果验证码发送号码固定，则可以设置验证码发送完整号码
                //.smsBodyStartWith("百度科技") // 设置验证码短信开头文字
                .smsBodyContains("验证码") // 设置验证码短信内容包含文字
                .build();
        AuthCode.getInstance().with(mActivity).config(config).into(tvCode);*/

    }

    @OnClick({R.id.btn_start, R.id.btn_end})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start:

                break;
            case R.id.btn_end:
                getContentResolver().unregisterContentObserver(smsObserver);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
