package com.varmin.vdemo.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.varmin.vdemo.R;
import com.varmin.vdemo.test.daggerTest.DaggerZaiNanComponent;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private TestActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        setContentView(R.layout.activity_test);
        View btnTest = findViewById(R.id.btn_test);
        btnTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String content = DaggerZaiNanComponent.builder().build().waimai().toString();
        Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
    }
}
