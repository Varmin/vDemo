package com.varmin.vdemo.net;


import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 以OKhttp为例
 * 1.正常是：
 *      别人博客入门--》实践--》反思、总结
 *      深入读更细致源码 --》 了解设置模式、整体流程架构
 * 问题：
 * 1. 不是纯粹的过程，不能读懂博客、不能读懂源码、现情况不能静下心来实践
 * 2. 像Gradle一样，一知半解不能再深入进去；
 *      * 不能读懂
 *      * 感觉是一个超大工程：精力、效率都感觉不能完成。
 *
 * 安排：你的主要任务是面试
 *      * 现不能正常一步步完成上面的理想步骤
 *      * 各个点照顾到，一知半解知道就行？
 *      * 只针对面试题？
 *      * 按计划硬着头皮？----心态、方法、时间、效率都不允许
 *
 * 以下点：
 *  学习程度：博客入门---》 源码：流程、设计模式、原理 ---》有重点的面试 ：迷迷瞪瞪，模模糊糊70~80分可以吧！
 *  （毕竟和30~50一样，像鸿阳、捷特烈那些人也是少之又少！你要的是努力达到80分就行！以后才有机会拼更多！）
 *  时间：10~2；2~6；7~12；周末很重要！顶的上一星期的每天2小时！！！！！
 *  其它：一周CP、抖音、试错都停了，这一个月；周末特别的重要！；
 *
 *
 * 有重点：小公司还是很垃圾，稍微正规的就得需要这些了
 * 网络、线程、性能、进程通信、图片、View、事件分发、消息机制、Activity/Fragment、四大组件、SP
 * RecyclerView、
 * RxJava、EventBus、ButterKnife、Glide、GreenDao等流行库原理
 * Kotlin、Flutter、组件化
 * 适配、推送、保活、缓存、定位
 * 艺术探索、HenCoder：基本就完全够了！
 *
 * 这些够了，剩下再专研一两个特别牛逼的点：Https（Android和前端以后都要用，趋势）、View
 *
 * -----------------
 *
 * 7月3日：按计划时间走，没做完就算了，下一个！
 * OKHttp、Retrofit：截止这周末，大概用法，重点源码；（面点查、总结）
 *  * 张拭新、伯特：responseBody只读取一次，都是遇到过了还不知道为什么；
 *    大家都一样啊！都是跌跌撞撞往前走；只不过别人人是摸索着用得到的更精通，你是因为反复，失去了最好的成长机会！
 *
 * 7月8日：按计划时间走，没做完就算了，下一个！
 * Gradle小例子、Kotlin语法
 * Retrofit、RxJava
 *
 * Kotlin：一周上午
 * 总结：网络、缓存、证书
 *
 *-------
 *
 * RecyclerView优化
 * Kotlin项目、框架分析
 *
 * 性能
 * Kotlin项目、框架分析
 *
 * 线程
 * 框架分析
 *
 * 进程间通信、Binder
 * 组件化、框架分析
 *
 *
 * 回顾：View、事件、消息、Activity...
 * 重要技术：适配、通知、crash、埋点
 * （2周）
 *
 * 一个半月，面试尝试：判断下一步计划（20以上换）
 *
 * 面试后看情况：
 * Https、证书、TCP---> 笔记，每隔一段时间复习（这是你的主打点）：笔记、课程（第4、5节）、书签
 *      缓存机制
 *
 *
 * 换工作后：
 * 广度培养专业技能：深度已经达不到了，那就从产品、管理、运营等角度试一试
 * 试错不能停止：Python
 * 私活
 *
 *
 *
 *
 */
public class OkHttpTestActivity extends BaseActivity {
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_ok_http_test;
    }

    @OnClick({R.id.btn_get, R.id.btn_post, R.id.btn_retrofit_get, R.id.btn_retrofit_post})
    public void onViewClicked(View view){
        switch (view.getId()) {
            case R.id.btn_get:
                getCommonList();
                break;
            case R.id.btn_post:

                break;
            case R.id.btn_retrofit_get:
                getCommonListRetrofit();
                break;
            case R.id.btn_retrofit_post:

                break;
        }
    }
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>---Retrofit---begin--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    private void getCommonListRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiRetrofit.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiRetrofit apiRetrofit = retrofit.create(ApiRetrofit.class);

        retrofit2.Call<Result<List<Chapter>>> chapters = apiRetrofit.chapters();
        chapters.enqueue(new retrofit2.Callback<Result<List<Chapter>>>() {
            @Override
            public void onResponse(retrofit2.Call<Result<List<Chapter>>> call, retrofit2.Response<Result<List<Chapter>>> response) {
                    Log.d(TAG, "onResponse: "+response.message()+", "+response.errorBody()+", "+response.body().errorCode);
            }

            @Override
            public void onFailure(retrofit2.Call<Result<List<Chapter>>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());
            }
        });
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>---Okhttp---begin--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
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
        private final Charset UTF8 = Charset.forName("UTF-8");
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            /**
             * pre：对参数进行封装：校验参数、添加公有参数、打印参数url等信息...
             */
            HttpUrl httpUrl = originalRequest.url().newBuilder().addQueryParameter("key", "varmin").build();
            Request request = originalRequest.newBuilder().url(httpUrl).build();
            Log.d(TAG, "intercept: request: "+request.toString());
            for (String queryParameterName : request.url().queryParameterNames()) {
                Log.d(TAG, "intercept: paramName = "+queryParameterName);
            }

            Response response = chain.proceed(request);

            /**
             * after：对返回内容：长度、格式、打印等控制
             */
            ResponseBody responseBody = response.body();
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.getBuffer();
            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            //clone：不会引起callback中responseBody的close
            Log.d(TAG, "intercept: response"+buffer.clone().readString(charset));
            return response;
        }
    }
}
/**




 */