package com.varmin.vdemo.net;


import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class OkHttpTestActivity extends BaseActivity {
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_ok_http_test;
    }

    @OnClick({R.id.btn_get, R.id.btn_post})
    public void onViewClicked(View view){
        switch (view.getId()) {
            case R.id.btn_get:
                getCommonList();
                break;
            case R.id.btn_post:

                break;
        }
    }


    public void getCommonList(){
        OkHttpClient okClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Api.COMMON_LIST)
                .build();
        Call newCall = okClient.newCall(request);
        newCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: "+e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: "+response);
            }
        });
    }
}
