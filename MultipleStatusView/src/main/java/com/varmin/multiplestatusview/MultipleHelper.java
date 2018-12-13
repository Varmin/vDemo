package com.varmin.multiplestatusview;

import android.util.Log;

import java.util.HashMap;

/**
 * Created by HuangYang
 * on 2018/12/12  16:22.
 * 文件描述：初始化各个View。
 */
public class MultipleHelper {
    private static final String TAG = "MultipleHelper";
    public static final int DEFAULT_NO_VALUE = 0;
    public final int DEFAULT_REFRESH_LAYOUT = R.layout.loading_status;
    public final int DEFAULT_LOADING_LAYOUT = R.layout.loading_status;
    public final int DEFAULT_EMPTY_LAYOUT = R.layout.empty_status;
    public final int DEFAULT_ERR_NET_LAYOUT = R.layout.err_net_status;
    public final int DEFAULT_ERROR_LAYOUT = R.layout.error_status;
    public final int DEFAULT_SUCCESS_LAYOUT = R.layout.success_status;


    public static MultipleHelper helper;
    private final HashMap<String, Integer> multipleMap;
    private HashMap<String, Integer> multipleDefaultMap;

    public static MultipleHelper getHelper(){
        if (helper == null) {
            helper = new MultipleHelper();
        }
        Log.e(TAG, "getHelper: helper="+helper.toString() );
        return helper;
    }
    private MultipleHelper(){
        Log.e(TAG, "MultipleHelper: init map" );
        multipleMap = new HashMap<>();
        initDefaultMap();
    }

    private void initDefaultMap() {
        multipleDefaultMap = new HashMap<>();
        multipleDefaultMap.put(Status.REFRESH, DEFAULT_REFRESH_LAYOUT);
        multipleDefaultMap.put(Status.LOADING, DEFAULT_LOADING_LAYOUT);
        multipleDefaultMap.put(Status.EMPTY, DEFAULT_EMPTY_LAYOUT);
        multipleDefaultMap.put(Status.ERR_NET, DEFAULT_ERR_NET_LAYOUT);
        multipleDefaultMap.put(Status.ERROR, DEFAULT_ERROR_LAYOUT);
        multipleDefaultMap.put(Status.SUCCESS, DEFAULT_SUCCESS_LAYOUT);
    }

    public MultipleHelper setRefresh(int layoutId){
        multipleMap.put(Status.REFRESH, layoutId);
        return this;
    }

    public MultipleHelper setLoading(int layoutId){
        multipleMap.put(Status.LOADING, layoutId);
        return this;
    }
    public MultipleHelper setEmpty(int layoutId){
        multipleMap.put(Status.EMPTY, layoutId);
        return this;
    }
    public MultipleHelper setErrNet(int layoutId){
        multipleMap.put(Status.ERR_NET, layoutId);
        return this;
    }
    public MultipleHelper setError(int layoutId){
        multipleMap.put(Status.ERROR, layoutId);
        return this;
    }
    public MultipleHelper setSuccess(int layoutId){
        multipleMap.put(Status.SUCCESS, layoutId);
        return this;
    }

    public int getView(@Status String status){
        if (multipleMap.get(status) != null) {
            return multipleMap.get(status);
        }
        return DEFAULT_NO_VALUE;
    }

    public int getDefault(@Status String status) {
        if (multipleDefaultMap.get(status) != null) {
            return multipleDefaultMap.get(status);
        }
        return DEFAULT_NO_VALUE;
    }
}
