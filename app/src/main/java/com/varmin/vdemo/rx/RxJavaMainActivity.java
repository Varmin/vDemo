package com.varmin.vdemo.rx;

import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;

import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static android.os.Build.VERSION_CODES.N;

@RequiresApi(N)
public class RxJavaMainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rx_java_main;
    }

    @OnClick({R.id.btn_rx_main_1, R.id.btn_rx_main_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_rx_main_1:
                map();
                break;
            case R.id.btn_rx_main_2:
                zip();
                break;
        }
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>---zip---begin--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * obable1、obable2: 同一个线程和不同线程的区别
     */
    private void zip() {
        Observable<Integer> obable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG, "subscribe1: Thread.name " + Thread.currentThread().getName());
                for (int i = 0; i < 10; i++) {
                    Log.d(TAG, "subscribe1: emitter " + i);
                    e.onNext(i);
                }
                Log.d(TAG, "subscribe1: onComplete--1 ");
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());
        Observable<String> obable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.d(TAG, "subscribe2: Thread.name " + Thread.currentThread().getName());
                for (int i = 0; i < 5; i++) {
                    Log.d(TAG, "subscribe2: emitter " + "00" + i);
                    e.onNext("00" + i);
                    Thread.sleep(1000);
                }
                Log.d(TAG, "subscribe2: onComplete--2 ");
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observable.zip(obable1, obable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return "zip " + integer + s;
            }
        }).subscribe(observerStr);
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>---map、flatmap---begin--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    ObservableOnSubscribe observable = new ObservableOnSubscribe<Integer>() {
        @Override
        public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
            Log.d(TAG, "subscribe: emitter next 1");
            emitter.onNext(1);
//                emitter.onComplete();
            Log.d(TAG, "subscribe: emitter next 2");
            emitter.onNext(2);
//                emitter.onError(new Throwable("emitter error"));
            Log.d(TAG, "subscribe: emitter next 3");
            emitter.onNext(3);
        }
    };
    Observer<String> observerStr = new Observer<String>() {
        @Override
        public void onSubscribe(Disposable d) {
            Log.d(TAG, "onSubscribe: " + d.toString() + ", " + d.isDisposed());
        }

        @Override
        public void onNext(String value) {
            Log.d(TAG, "onNext: " + value);
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            Log.d(TAG, "onError: " + e.toString());
        }

        @Override
        public void onComplete() {
            Log.d(TAG, "onComplete: Done");
        }
    };
    Consumer consumer = new Consumer<Integer>() {
        @Override
        public void accept(Integer integer) {
            Log.d(TAG, "accept --------- int : " + integer);
        }
    };
    Consumer consumerStr = (Consumer<String>) string -> Log.d(TAG, "accept --------- str: " + string);

    private void map() {
        Observable.create(observable)
                .doOnNext((Consumer<Integer>) o -> Log.d(TAG, "accept: doOnNext " + o))
                .map((Function<Integer, String>) integer -> {
                    Log.d(TAG, "apply: map " + integer);
                    return integer + "";
                })
                .flatMap((Function<String, ObservableSource<String>>) s -> {
                    Log.d(TAG, "apply: flatmap " + s);
                    ArrayList<String> list = new ArrayList<>();
                    for (int i = 0; i < 3; i++) {
                        list.add("from flatmap " + s + ", " + i);
                    }
                    Observable<String> source = Observable.fromIterable(list);
                    source.delay(1000, TimeUnit.MILLISECONDS);
                    return source;
                })
                .subscribe(observerStr);

/*
        Single.just(1)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return integer+"";
                    }
                }).subscribe();*/
    }
}
