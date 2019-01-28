package com.varmin.vdemo.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ptbaby.applypermissions.Permissions;
import com.putibaby.common.media.MediaSelect;
import com.varmin.vdemo.R;
import com.varmin.vdemo.test.daggerTest.DaggerFoodComponent;
import com.varmin.vdemo.test.daggerTest.DaggerZaiNanComponent;
import com.varmin.vdemo.test.daggerTest.FoodComponent;
import com.varmin.vdemo.test.daggerTest.Girl;
import com.varmin.vdemo.test.daggerTest.ZaiNanComponent;

import javax.inject.Inject;

import apro.com.varmin.lib_1.Lib_1;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "TestActivity";
    private TestActivity mContext;
    @Inject
    Girl girl;
    private ZaiNanComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        setContentView(R.layout.activity_test);
        View btnTest = findViewById(R.id.btn_test);
        btnTest.setOnClickListener(this);

        FoodComponent foodComponent = DaggerFoodComponent.builder().build();

        component = DaggerZaiNanComponent.builder()
                .foodComponent(foodComponent)
                .build();
        component.inject(this);

        Lib_1 lib_1 = new Lib_1();
        String a = Permissions.ACCESS_COARSE_LOCATION;
    }

    @Override
    public void onClick(View v) {
        String content = component.zaiNan().toString() + "-" + girl.toString();
        Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
    }
}
