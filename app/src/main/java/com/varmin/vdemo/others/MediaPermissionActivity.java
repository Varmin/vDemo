package com.varmin.vdemo.others;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.ptbaby.applypermissions.PermissionUtils;
import com.ptbaby.applypermissions.Permissions;
import com.ptbaby.applypermissions.callback.MultiplePermissionCallback;
import com.putibaby.common.media.MediaSelect;
import com.putibaby.common.media.Type;
import com.varmin.vdemo.R;

import java.util.List;

public class MediaPermissionActivity extends Activity {
    private static final String TAG = "MainActivity";
    private MediaPermissionActivity mActivity;
    private List<String> mSelectedList;
    private PermissionUtils pUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);
        mActivity = this;

        pUtils = new PermissionUtils(mActivity);
        pUtils.setOnPermissionCallback(new MultiplePermissionCallback() {
            @Override
            public void hasAllPermissionsStatus(int requestCode, @NonNull String[] perms) {
                Log.d(TAG, "hasAllPermissionsStatus: ");
            }

            @Override
            public void permissionGrantedOrDeniedStatus(int requestCode, @NonNull String[] permsGranted, @NonNull String[] permsDenied) {
                Log.d(TAG, "permissionGrantedOrDeniedStatus: ");
            }
        });
        findViewById(R.id.btn_take).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pUtils.applyPermissions(Permissions.READ_EXTERNAL_STORAGE, Permissions.CAMERA);
            }
        });

        findViewById(R.id.btn_type).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaSelect.getInstance(mActivity)
                        .setItemsType(new MediaSelect.MediaTypeClickListener() {
                            @Override
                            public void albumCustom(List<String> imagePathList) {
                                super.albumCustom(imagePathList);
                                mSelectedList = imagePathList;
                                for (String s : imagePathList) {
                                    Log.d(TAG, "albumCustom: "+s);
                                }
                            }

                            @Override
                            public void cameraPhoto(String imagePath) {
                                super.cameraPhoto(imagePath);
                                Log.d(TAG, "cameraPhoto: "+imagePath);
                            }

                            @Override
                            public void cropPhoto(String photoPath, String cropPhotoPath) {
                                super.cropPhoto(photoPath, cropPhotoPath);
                                Log.d(TAG, "cameraCropPhoto: "+photoPath+", "+cropPhotoPath);
                            }

                            @Override
                            public void cancel(int requestCode, int resultCode, String msg) {
                                super.cancel(requestCode, resultCode, msg);
                                Log.d(TAG, "cancel: msg="+msg);
                            }

                            @Override
                            public void error(String type, String msg) {
                                super.error(type, msg);
                            }
                        }, Type.ALBUM_CUSTOM,Type.ALBUM_CLIP_CUSTOM,  Type.CAMERA_PHOTO, Type.CAMERA_CLIP_PHOTO)
                        .setPickInfo(5, null)
                        .show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MediaSelect.getInstance(mActivity).onRequestPermissionsResult(requestCode, permissions, grantResults
                , Type.ALBUM_CUSTOM,Type.ALBUM_CLIP_CUSTOM, Type.CAMERA_PHOTO, Type.CAMERA_CLIP_PHOTO);
        pUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MediaSelect.getInstance(mActivity).onActivityResult(requestCode, resultCode, data);
    }
}
