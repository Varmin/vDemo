package com.varmin.permissionapply;

import android.support.annotation.NonNull;

/**
 * Created by HuangYang
 * on 2018/12/28  10:52.
 * 文件描述：
 */
public class PermissionCallback implements Callback {
    @Override
    public void singlePermission(int requestCode, boolean hasPermission, @NonNull String permission) {

    }

    @Override
    public void hasAllPermissions(int requestCode, @NonNull String[] perms) {

    }

    @Override
    public void permissionStatus(int requestCode, @NonNull String[] permsGranted, @NonNull String[] permsDenied) {

    }
}
