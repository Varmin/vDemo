package com.varmin.vdemo.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.varmin.vdemo.R;
import com.varmin.vdemo.test.daggerTest.DaggerFoodComponent;
import com.varmin.vdemo.test.daggerTest.DaggerZaiNanComponent;
import com.varmin.vdemo.test.daggerTest.FoodComponent;
import com.varmin.vdemo.test.daggerTest.Girl;
import com.varmin.vdemo.test.daggerTest.ZaiNanComponent;

import javax.inject.Inject;

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
    }

    @Override
    public void onClick(View v) {
        String content = component.zaiNan().toString() + "-" + girl.toString();
        Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
    }
}
