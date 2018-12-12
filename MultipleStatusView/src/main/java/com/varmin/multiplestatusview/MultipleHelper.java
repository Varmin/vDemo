package com.varmin.multiplestatusview;

import java.util.HashMap;

/**
 * Created by HuangYang
 * on 2018/12/12  16:22.
 * 文件描述：初始化各个View。
 */
public class MultipleHelper {
    public final int DEFAULT_LOADING_LAYOUT = R.layout.loading_status;
    public final int DEFAULT_EMPTY_LAYOUT = R.layout.empty_status;
    public final int DEFAULT_ERR_NET_LAYOUT = R.layout.err_net_status;
    public final int DEFAULT_ERROR_LAYOUT = R.layout.error_status;
    public final int DEFAULT_SUCCESS_LAYOUT = R.layout.success_status;


    public static MultipleHelper helper;
    private final HashMap<String, Integer> multipleMap;
    private final HashMap<String, Integer> multipleDefaultMap;

    public static MultipleHelper getHelper(){
        if (helper == null) {
            helper = new MultipleHelper();
        }
        return helper;
    }
    private MultipleHelper(){
        multipleMap = new HashMap<>();
        multipleDefaultMap = new HashMap<>();
        initDefaultMap();
    }

    private void initDefaultMap() {
        multipleDefaultMap.put(Status.LOADING, R.layout.loading_status);
        multipleDefaultMap.put(Status.EMPTY, R.layout.empty_status);
        multipleDefaultMap.put(Status.ERR_NET, R.layout.err_net_status);
        multipleDefaultMap.put(Status.ERROR, R.layout.error_status);
        multipleDefaultMap.put(Status.SUCCESS, R.layout.success_status);
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
        return multipleMap.get(status);
    }

    public int getDefault(@Status String status) {
        return multipleDefaultMap.get(status);
    }
}
