package com.varmin.vdemo.others;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.Telephony;
import android.text.TextUtils;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by HuangYang
 * on 2018/12/18  11:20.
 * 文件描述：
 */
public class SMSContentObserver extends ContentObserver {
    private static final String TAG = "SMSContentObserver";
    private final Context mContext;
    private final Handler mHandler;
    //API level>=23,可直接使用Telephony.Sms.Inbox.CONTENT_URI
    public static final String SMS_INBOX_URI = "content://sms/inbox";
    public static final String SMS_URI = "content://sms";
    public static final int RECEIVER_SMS_CODE_MSG = 0;
    static final String[] PROJECTION = new String[]{
            Telephony.Sms._ID,
            Telephony.Sms.ADDRESS,
            Telephony.Sms.BODY,
            Telephony.Sms.DATE
    };

    public SMSContentObserver(Context context, Handler handler) {
        super(handler);
        this.mContext = context;
        this.mHandler = handler;
        Log.d(TAG, "SMSContentObserver: init");
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
        Log.d(TAG, "onChange: "+selfChange+", uri="+uri);
        if (!uri.toString().contains(SMS_INBOX_URI)) return;
        Cursor cursor = mContext.getContentResolver().query(Uri.parse(SMS_INBOX_URI), null,
                null, null, Telephony.Sms.Inbox.DEFAULT_SORT_ORDER);
        getSmsCodeFromObserver(cursor);
    }


    void getSmsCodeFromObserver(Cursor cursor) {
        if (cursor == null) return;
        Log.d(TAG, "getSmsCodeFromObserver: cursor="+cursor+", "+cursor.getCount());
        while (cursor.moveToNext()) {
            String address = cursor.getString(cursor.getColumnIndex(Telephony.Sms.ADDRESS));
            String smsBody = cursor.getString(cursor.getColumnIndex(Telephony.Sms.BODY));
            Log.d(TAG, "getSmsCodeFromObserver: address="+address+", smsBody="+smsBody);
            if (!TextUtils.isEmpty(smsBody) && smsBody.contains("优酷")) {
                String smsCode = parseSmsBody(smsBody);
                sendCode(smsCode);

                Log.d(TAG, "getSmsCodeFromObserver: smsBody="+smsBody);
                //break;
            }
        }
        cursor.close();
        Log.d(TAG, "getSmsCodeFromObserver: close");
    }

    /**
     * 解析短信得到验证码
     */
    private String parseSmsBody(String smsBody) {
        String regex = new String("(\\d{" + 6 + "})");// 匹配规则为短信中的连续数字
        String smsCode = "";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(smsBody);
        while (matcher.find()) {
            smsCode = matcher.group(0);
        }
        return smsCode;
    }

    private void sendCode(String smsCode) {
        if (mHandler != null) {
            Message msg = mHandler.obtainMessage();
            msg.what = RECEIVER_SMS_CODE_MSG;
            msg.obj = smsCode;
            mHandler.sendMessage(msg);
        }
    }



}
