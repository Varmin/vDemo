package com.varmin.vdemo.net;


import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
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
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okClient = new OkHttpClient.Builder()
                .addInterceptor(new TestInterceptor())
                .addInterceptor(logging)
                .build();
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
                Log.d(TAG, "onResponse: "+response.body().string());
            }
        });

    }

    public class TestInterceptor implements Interceptor{
        private static final String TAG = "TestInterceptor";
        private final Charset UTF8 = Charset.forName("UTF-8");
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();

            //todo 公有参数封装，get、post
            HttpUrl httpUrl = originalRequest.url().newBuilder().addQueryParameter("key", "varmin").build();
            Request request = originalRequest.newBuilder().url(httpUrl).build();
            Log.d(TAG, "intercept: request"+request.toString());
            for (String queryParameterName : request.url().queryParameterNames()) {
                Log.d(TAG, "intercept: paramName = "+queryParameterName);
            }


            Response response = chain.proceed(request);

            ResponseBody responseBody = response.body();
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.getBuffer();
            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            Log.d(TAG, "intercept: response"+buffer.clone().readString(charset));
            return response;
        }
    }
}
