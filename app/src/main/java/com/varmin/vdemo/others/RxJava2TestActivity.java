package com.varmin.vdemo.others;


import android.util.Log;
import android.widget.Button;
import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class RxJava2TestActivity extends BaseActivity {
    private static final String TAG = "RxJava2TestActivity";
    @BindView(R.id.btn_run)
    Button btnRun;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rx_java2_test;
    }


    @OnClick(R.id.btn_run)
    public void onViewClicked() {
        run();
    }

    private void run() {
        Observable
        .create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG, "subscribe: ");
                Log.d(TAG, "subscribe: next-1");
                e.onNext(1);
                Log.d(TAG, "subscribe: next-2");
                e.onNext(2);
                Log.d(TAG, "subscribe: next-3");
                e.onNext(3);
                //Log.d(TAG, "subscribe: onComplete");
                //e.onComplete();
                Log.d(TAG, "subscribe: next-4");
                e.onNext(4);
                Log.d(TAG, "subscribe: next-5");
                e.onNext(5);
            }
        })
        .map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "this value is "+integer;
            }
        })
        .flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(String s) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    s += "_"+i;
                    list.add(s);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        })
        .subscribe(new Observer<String>() {
            private Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");
                this.mDisposable = d;
            }

            @Override
            public void onNext(String value) {
                Log.d(TAG, "onNext: "+value);
                if (value.contains("is 2")) {
                    //mDisposable.dispose();
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        });
    }
}
