package com.varmin.permissionapply;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by HuangYang
 * on 2018/12/25  16:01.
 * 文件描述：
 */
public class PermissionUtils {
    private static final String TAG = "PermissionUtils";
    public static final int REQUEST_CODE = 1111;

    private final Activity mActivity;

    private final PermissionUtils mInstance;
    private String[] mPermissions;
    private PermissionCallback mPermissionCallBack;

    public PermissionUtils(Activity activity){
        this.mActivity = activity;
        this.mInstance = this;
    }

    public void applyPermissions(@NonNull String... perms){
        this.mPermissions = perms;
        if (perms == null || (perms != null && perms.length == 0)) {
            return;
        }
        List<String> noPermissionList = hasPermissions(perms);
        if (noPermissionList.size() != 0) {
            ActivityCompat.requestPermissions(mActivity, noPermissionList.toArray(new String[noPermissionList.size()]), REQUEST_CODE);
            Log.d(TAG, "hasPermissions_2: deniedList="+noPermissionList.size());
        }else {
            if (perms.length == 1) {
                if (mPermissionCallBack != null) mPermissionCallBack.singlePermission(REQUEST_CODE, true, perms[0]);
            }else {
                if (mPermissionCallBack != null) mPermissionCallBack.hasAllPermissions(REQUEST_CODE, perms);
            }
        }
    }

    public List<String> hasPermissions(@NonNull String... perms){
        ArrayList<String> moPermissionList = new ArrayList<>();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            Log.w(TAG, "hasPermissions: API version < M, returning true by default");
            return moPermissionList;
        }
        if (mActivity == null) {
            throw new IllegalArgumentException("Can't check permissions for null context");
        }

        boolean hasPermissons = true;
        for (String perm : perms) {
            if (ContextCompat.checkSelfPermission(mActivity, perm) != PackageManager.PERMISSION_GRANTED) {
                hasPermissons = false;
                moPermissionList.add(perm);
            }
        }
        Log.d(TAG, "hasPermissions_1: moPermissionList="+moPermissionList.size()+", hasPermissons="+hasPermissons);
        return moPermissionList;
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        if (requestCode == REQUEST_CODE) {
            ArrayList<String> rationalList = new ArrayList<>();
            for (int i = 0; i < permissions.length; i++) {
                Log.d(TAG, "onRequestPermissionsResult: i="+i+", permission="+permissions[i]+", grant="+grantResults[i]);
                String perm = permissions[i];
                int grant = grantResults[i];
                if (grant == PackageManager.PERMISSION_DENIED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, perm)) {
                        rationalList.add(perm);
                    }
                }
            }
            if (rationalList.size() != 0) {
                showRationale(rationalList);
            }
            Log.d(TAG, "onRequestPermissionsResult: rational.Size="+rationalList.size());
            //没有能提醒的再一块结算
            ArrayList<String> deniedList = new ArrayList<>();
            ArrayList<String> grantList = new ArrayList<>();
            if(rationalList.size() == 0){
                for (String mPermission : mPermissions) {
                    if (ContextCompat.checkSelfPermission(mActivity, mPermission) == PackageManager.PERMISSION_GRANTED) {
                        grantList.add(mPermission);
                    }else {
                        deniedList.add(mPermission);
                    }
                }
                if (mPermissions.length == 1) {
                    if (mPermissionCallBack != null) mPermissionCallBack.singlePermission(REQUEST_CODE, grantList.size() == 1, mPermissions[0]);
                }else {
                    if (mPermissions.length == grantList.size()) {
                        if (mPermissionCallBack != null) mPermissionCallBack.hasAllPermissions(REQUEST_CODE, mPermissions);
                    }else {
                        if (mPermissionCallBack != null) mPermissionCallBack.permissionStatus(REQUEST_CODE
                                , grantList.toArray(new String[grantList.size()])
                                , deniedList.toArray(new String[deniedList.size()]));
                    }
                }
            }
        }

    }

    /**
     * 展示确认提醒
     */
    private void showRationale(final ArrayList<String> rationalList) {
        StringBuilder builder = new StringBuilder();
        for (String s : rationalList) {
            builder.append(s).append("\n");
        }
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(mActivity)
                .setMessage(builder.toString().trim())
                .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(mActivity, rationalList.toArray(new String[rationalList.size()]), REQUEST_CODE);
                    }
                });
        alertBuilder.create().show();
    }

    //todo 为什么在EasyPermissions中ActivityCompat源码指向support-compat-26.1.0有注释有什么的，在这里ActivityCompat指向class.jar包，没注释，没变量名
    public interface PermissionCallback{
        void singlePermission(int requestCode, boolean hasPermission,  @NonNull String permission);
        void hasAllPermissions(int requestCode, @NonNull String[] perms);
        void permissionStatus(int requestCode, @NonNull String[] permsGranted, @NonNull String[] permsDenied);
    }
    public PermissionUtils setOnPermissionCallback(PermissionCallback callback){
        this.mPermissionCallBack = callback;
        return mInstance;
    }
}
