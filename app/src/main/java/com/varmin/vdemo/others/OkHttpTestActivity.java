package com.varmin.vdemo.others;


import android.view.View;
import android.widget.TextView;
import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;
import java.io.IOException;
import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
                getExecute();
                break;
            case R.id.btn_post:

                break;
        }
    }

    private void getExecute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient mOkClient = new OkHttpClient();
                    final Request request = new Request.Builder().url("http://wanandroid.com/wxarticle/chapters/json").build();
                    final Response response = mOkClient.newCall(request).execute();
                    final String info = response.body().string();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvInfo.setText(info);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void postExecute(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String host = "http://172.26.133.50//testpost.php";
                final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                OkHttpClient client = new OkHttpClient();
                String json = "{\"username\":\"frank\",sex:\"man\"}";
                RequestBody body = RequestBody.create(JSON, json);
                Request request = new Request.Builder()
                        .url(host)
                        .post(body)
                        .build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    final String info = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvInfo.setText(info);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }

    private void getEnqueue(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://blog.csdn.net/briblue").build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String info = response.body().string();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvInfo.setText(info);
                            }
                        });
                    }
                });
            }
        }).start();
    }
}
