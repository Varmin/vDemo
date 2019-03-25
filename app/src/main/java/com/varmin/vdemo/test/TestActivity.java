package com.varmin.vdemo.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.varmin.vdemo.R;
import com.varmin.vdemo.test.daggerTest.DaggerFoodComponent;
import com.varmin.vdemo.test.daggerTest.DaggerZaiNanComponent;
import com.varmin.vdemo.test.daggerTest.FoodComponent;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "TestActivity";
    private TestActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        setContentView(R.layout.activity_test);





    }

    public void setListener(Listener<Person> listener){

    }
    public class Person{}
    public abstract class Listener<T>{
        public void set(){
            Type[] types = this.getClass().getGenericInterfaces();
            Type[] params = ((ParameterizedType) types[0]).getActualTypeArguments();
            Class<T> reponseClass = (Class) params[0];
            T a = new Gson().fromJson("", reponseClass);
        }
        void get(T t){};
    }



    @Override
    public void onClick(View v) {
        /**
         * Looper: ThreadLocal
         * Thread: ThreadLocalMap （key：threadLocal, value: value）
         * ThreadLocal: 从Thread中的map中存取
         */
    }
}
