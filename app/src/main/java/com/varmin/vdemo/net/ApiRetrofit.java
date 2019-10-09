package com.varmin.vdemo.net;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by HuangYang
 * on 2019-06-25  10:29.
 * 文件描述：
 */
public interface ApiRetrofit {
    String BASE_URL = "https://wanandroid.com/";

    @GET("wxarticle/chapters/json")
    Call<Result<List<Chapter>>> chapters();

    @GET("wxarticle/chapters/json")
    Observable<Result<List<Chapter>>> chapters_rx();
}
