package com.varmin.permissionapply;

import android.support.annotation.NonNull;

/**
 * Created by HuangYang
 * on 2018/12/28  10:51.
 * 文件描述：
 */
public interface Callback {
    void singlePermission(int requestCode, boolean hasPermission,  @NonNull String permission);
    void hasAllPermissions(int requestCode, @NonNull String[] perms);
    void permissionStatus(int requestCode, @NonNull String[] permsGranted, @NonNull String[] permsDenied);
}
