package com.varmin.vdemo.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.varmin.vdemo.R;
import com.varmin.vdemo.test.daggerTest.DaggerZaiNanComponent;
import com.varmin.vdemo.test.daggerTest.Noodle;
import com.varmin.vdemo.test.daggerTest.ZaiNanComponent;

import javax.inject.Inject;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "TestActivity";
    private TestActivity mContext;
    @Inject
    Noodle noodle;
    private ZaiNanComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        setContentView(R.layout.activity_test);
        View btnTest = findViewById(R.id.btn_test);
        btnTest.setOnClickListener(this);

        component = DaggerZaiNanComponent.builder().build();
        component.inject(this);
        Log.d(TAG, "onCreate: "+noodle.toString());
    }

    @Override
    public void onClick(View v) {

        Toast.makeText(mContext, component.waimai().toString(), Toast.LENGTH_SHORT).show();
    }
}
