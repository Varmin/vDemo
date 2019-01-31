package com.varmin.vdemo.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.putibaby.common.video.VideoRecordActivity;
import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;

import butterknife.BindView;

public class FragmentMainActivity extends BaseActivity {
    private static final String TAG = "FragmentMainActivity";
    @BindView(R.id.btn_get)
    TextView tvGet;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment_main;
    }

    @Override
    protected void initData() {
        super.initData();
        SharedPreferences sp = getSharedPreferences("varmin", Context.MODE_PRIVATE);

        tvGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(FragmentMainActivity.this, VideoRecordActivity.class), 111);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: data="+data);
        if (requestCode == 111) {
            String dataStr = data.getStringExtra("data");
            Log.d(TAG, "onActivityResult: dataStr="+dataStr);
        }
    }
}
