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
import io.reactivex.functions.Consumer;
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

    }
}
