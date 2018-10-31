package com.varmin.vdemo.others;

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
    AppCompatSeekBar seekBarSample;
    @BindView(R.id.pgb_image_compress)
    AppCompatSeekBar seekBarCompress;


    public enum Type {
        sample,
        compress
    }
    private Type type = Type.sample;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_load_big_bitmap;
    }

    @Override
    protected void initData() {
        seekBarSample.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStopTrackingTouch: "+seekBar.getProgress());
                currProgress = (float) (100 - seekBar.getProgress()) / 100;
                type = Type.sample;
                onWindowFocusChanged(true);
            }
        });
        seekBarCompress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStopTrackingTouch: "+seekBar.getProgress());
                currProgress = (float) (100 - seekBar.getProgress()) / 100;
                type = Type.compress;
                onWindowFocusChanged(true);
            }
        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            switch (type) {
                case sample:
                    imageInfoView.setBitmapSample(R.drawable.big_2, (int)(imageInfoView.getWidth()*currProgress), (int) (imageInfoView.getHeight()*currProgress));
                    break;
                case compress:
                    imageInfoView.setBitmapCompress((int) (currProgress*100));
                    break;
            }
        }
    }
}


