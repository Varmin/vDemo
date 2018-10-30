package com.varmin.vdemo.others;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.Log;
import android.widget.SeekBar;
import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;
import com.varmin.vdemo.view.ImageInfoView;

import butterknife.BindView;

public class LoadBigBitmapActivity extends BaseActivity {
    private static final String TAG = "LoadBigBitmapActivity";

    private float currProgress = 1;


    @BindView(R.id.iiv_image_info)
    ImageInfoView imageInfoView;
    @BindView(R.id.pgb_image_sample)
    AppCompatSeekBar progressBar;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_load_big_bitmap;
    }

    @Override
    protected void initData() {
        progressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "onProgressChanged: progress="+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStartTrackingTouch: "+seekBar.getProgress());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStopTrackingTouch: "+seekBar.getProgress());
                currProgress = (float) (100 - seekBar.getProgress()) / 100;
                onWindowFocusChanged(true);
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
//            imageInfoView.setBitmapSample(R.mipmap.big_2_xxh, (int)(imageInfoView.getWidth()*currProgress), (int) (imageInfoView.getHeight()*currProgress));
           //imageInfoView.setBitmapSample(R.drawable.big_2, (int)(imageInfoView.getWidth()*currProgress), (int) (imageInfoView.getHeight()*currProgress));
            imageInfoView.settBitmapCompress((int) (currProgress*100), "/sdcard/bitmap.jpg");
        }
    }
}
