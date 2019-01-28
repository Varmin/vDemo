package com.varmin.vdemo.others;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Button;

import com.ptbaby.applypermissions.PermissionUtils;
import com.ptbaby.applypermissions.Permissions;
import com.ptbaby.applypermissions.callback.PermissionCallback;
import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class PermissionActivity extends BaseActivity{
    private static final String TAG = "PermissionActivity";

    @BindView(R.id.tv_permission)
    Button tvPermission;
    private PermissionUtils mPermsUtils;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_permission;
    }

    @Override
    protected void initData() {
        super.initData();

        mPermsUtils = new PermissionUtils(this);
    }

    @OnClick(R.id.tv_permission)
    public void onViewClicked() {
        mPermsUtils.setOnPermissionCallback(new PermissionCallback(){
            @Override
            public void singlePermission(int requestCode, boolean hasPermission, @NonNull String permission) {
                super.singlePermission(requestCode, hasPermission, permission);
                Log.d(TAG, "singlePermission: "+hasPermission+", "+permission);
            }

            @Override
            public void hasAllPermissions(int requestCode, @NonNull String[] perms) {
                super.hasAllPermissions(requestCode, perms);
                Log.d(TAG, "hasAllPermissions: "+perms.length);
            }

            @Override
            public void permissionGrantedOrDenied(int requestCode, @NonNull String[] permsGranted, @NonNull String[] permsDenied) {
                super.permissionGrantedOrDenied(requestCode, permsGranted, permsDenied);
                Log.d(TAG, "permissionStatus: "+permsGranted.length+", "+permsDenied.length);
            }
        }).applyPermissions(Permissions.ACCESS_COARSE_LOCATION
        , Permissions.CAMERA
        , Permissions.CALL_PHONE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPermsUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
