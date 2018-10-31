package com.varmin.vdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.varmin.vdemo.R;
import com.varmin.vdemo.base.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by HuangYang
 * on 2018/10/27  20:04.
 * 文件描述：
 */
public class ImageInfoView extends AppCompatImageView {
    private static final String TAG = "ImageInfoView";
    private StringBuffer bitmapSizeInfo;

    public ImageInfoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private final TextPaint mPaint;

    {
        mPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(Utils.dp2px(15));
    }
    /**
     * 采样压缩
     * @param targetWidth
     * @param targetHeight
     */
    public void setBitmapSample(int resourceId, int targetWidth, int targetHeight){
        Log.d(TAG, "setBitmapSample: targetWidth="+targetWidth+", targetHeight="+targetHeight);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceId, options);
        getBitmapInfo(options, bitmap, null);
        int sampleSize = getSampleSize(options, targetWidth, targetHeight);
        options.inJustDecodeBounds = false;
        options.inSampleSize = sampleSize;
        bitmap = BitmapFactory.decodeResource(getResources(), resourceId, options);
        getBitmapInfo(options, bitmap, null);
        setImageBitmap(bitmap);
    }

    /**
     * 质量压缩
     */
    public void setBitmapCompress(int compress){
        StringBuilder tag = new StringBuilder();
        String filePath = Environment.getExternalStorageDirectory() + "/aaa.jpg";
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog, options);
        getBitmapInfo(options, bitmap, null);
        try {
            //保存压缩图片到本地
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, compress, fs);
            fs.flush();
            fs.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //查看压缩之后的 Bitmap 大小
        /*ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, compress, outputStream);
        byte[] bytes = outputStream.toByteArray();
        Bitmap bitmapNew = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        getBitmapInfo(null, bitmapNew);
        setImageBitmap(bitmapNew);*/
        BitmapFactory.Options optionsNew = new BitmapFactory.Options();
        File file = new File(filePath);
        tag.append("\n").append("fileSize="+file.length()+"kb, "+getMCount((int) file.length())+"M");
        Bitmap bitmapNew = BitmapFactory.decodeFile(filePath, optionsNew);
        getBitmapInfo(optionsNew, bitmapNew, tag.toString());
        setImageBitmap(bitmapNew);
    }

    private float getMCount(int count){
        float cc = (float) count / (1024 * 1024);
        return cc;
    }
    /**
     * 获取缩放比例
     */
    private int getSampleSize(BitmapFactory.Options options, int targetWidth, int targetHeight){
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        int inSampleSize;
        float ssWidth = (float)outWidth / targetWidth;
        float ssHeight = (float)outHeight/targetHeight;
        inSampleSize = (int) (ssWidth > ssHeight ? ssWidth : ssHeight);
        /*Log.d(TAG, "getSampleSize: outWidht="+outWidth+", targetWidht="+targetWidth+", size="+ssWidth+", round="+Math.round(outWidth/targetWidth));
        Log.d(TAG, "getSampleSize: outheidht="+outHeight+", targetHeight="+targetHeight+", size="+ssHeight+", round="+Math.round(outHeight/targetHeight));
        Log.d(TAG, "getSampleSize: inSampleSize="+inSampleSize);*/
        return inSampleSize;
    }

    /**
     * 动态按比例缩放
     * @param sample
     */
    public void setSampleSize(int sample){

    }

    /**
     *获取Bitmap信息
     */
    private String getBitmapInfo(BitmapFactory.Options options, Bitmap bitmap, String tag){
        StringBuilder builder = new StringBuilder();
        if (options != null) {
            builder.append("\n").append("options: ").append("\n")
                    .append("outWidth="+options.outWidth+", outHeight="+options.outHeight).append("\n")
                    .append("inJustDecodeBounds="+options.inJustDecodeBounds+", outMimeType="+options.outMimeType+", inBitmap="+options.inBitmap+", inMutable="+options.inMutable+", inSampleSize="+options.inSampleSize).append("\n")
                    .append("inDensity="+ options.inDensity+", inScreenDensity="+options.inScreenDensity+", inTargetDensity="+options.inTargetDensity).append("\n");
        }
        if (bitmap != null) {
            builder.append("config="+bitmap.getConfig()).append("\n")
                    .append("getByteCount="+bitmap.getByteCount()+", getAllocationByteCount="+bitmap.getAllocationByteCount()).append("\n")
                    .append("  getByteCount="+getMCount(bitmap.getByteCount())+"M, getAllocationByteCount="+getMCount(bitmap.getAllocationByteCount())+"M").append("\n");
        }
        Log.d(TAG, "getBitmapInfo: "+builder.toString());

        bitmapSizeInfo = new StringBuffer();
        if (options != null) {
            bitmapSizeInfo.append("w="+options.outWidth+", h="+options.outHeight).append("\n")
                    .append("inSample="+options.inSampleSize).append("\n")
                    .append("densitty="+options.inDensity+", tarDensity="+options.inTargetDensity).append("\n");
        }
        if (bitmap != null) {
            bitmapSizeInfo.append("size="+bitmap.getByteCount()+"kb, "+getMCount(bitmap.getByteCount())+"M");
        }
        if (!TextUtils.isEmpty(tag)) {
            bitmapSizeInfo.append(tag);
        }

        invalidate();
        return builder.toString();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (bitmapSizeInfo != null) {
            StaticLayout staticLayout = new StaticLayout(bitmapSizeInfo.toString(), mPaint, getWidth(), Layout.Alignment.ALIGN_NORMAL, 1, 0, true);
            staticLayout.draw(canvas);
            //canvas.drawText(bitmapSizeInfo.toString(), 10, 50, mPaint);
        }
    }
}

/**
 * outWidth=1072, outHeight=712
 * inDensity=320, inTargetDensity=560
 * config=ARGB_8888
 * getByteCount=9349984
 *
 * outWidth=1072, outHeight=712
 * c=160, inTargetDensity=560
 * config=ARGB_8888
 * getByteCount=37399936
 *
 * scale = inTargetDensity / inTargetDensity;
 * size = width * scale * height * scale * config
 * size = 1072 * 3.5 * 712 * 3.5 * 4 = 37399936
 */
