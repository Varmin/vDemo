package com.varmin.vdemo.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.varmin.vdemo.R;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "TestActivity";
    private TestActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        setContentView(R.layout.activity_test);





    }




    @Override
    public void onClick(View v) {
    }
}
