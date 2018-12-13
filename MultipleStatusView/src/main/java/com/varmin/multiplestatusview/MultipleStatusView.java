package com.varmin.multiplestatusview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HuangYang
 * on 2018/12/12  15:57.
 * 文件描述：
 */
public class MultipleStatusView extends FrameLayout implements MultipleStatus{

    private RelativeLayout.LayoutParams DEFAULT_PARAMS = new RelativeLayout.LayoutParams(-1, -1);
    private Map<String, View> statusViewMap = new HashMap<>();
    private LayoutInflater mInflate;
    private View mContentView;

    public MultipleStatusView(@NonNull Context context) {
        this(context, null);
    }

    public MultipleStatusView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultipleStatusView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr();
        initView();
    }

    private void initAttr() {

    }

    /**
     * TODO ： contentView 时机？子View何时加载进去？
     */
    private void initView() {
        mInflate = LayoutInflater.from(getContext());
    }

    /**
     * 布局添加的contentView
     * 动态添加的contentView + tag
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (getChildCount() != 1) {
            throw new NoContentViewException();
        }
        mContentView = getChildAt(0);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }




    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>---status---begin--->>>>>>>>>>>>>>>>>>>>>>>>>>
    @Override
    public void showContentView() {
        if (mContentView == null) {
            throw new NoContentViewException();
        }
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            childView.setVisibility(childView == mContentView ? VISIBLE:GONE);
        }
    }

    @Override
    public void showStatusView(View view) {
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            childView.setVisibility(childView == view ? VISIBLE:GONE);
        }
    }

    @Override
    public void showRefresh() {
        int helpId = getHelpView(Status.REFRESH);
        if (checkHelpView(helpId)) {
            showLoading(helpId);
        }else {
            showLoading(getHelpDefaultView(Status.REFRESH));
        }
    }

    @Override
    public void showRefresh(int layoutId) {
        View refreshView = getViewFromMap(Status.REFRESH);
        if (!checkViewMap(refreshView)) {
            refreshView = mInflate.inflate(layoutId, this);
            addViewToMap(Status.REFRESH, refreshView);

            addView(refreshView, DEFAULT_PARAMS);
        }
        //todo 不隐藏contentView，显示refresh
        showStatusView(refreshView);
    }

    /**
     * 检查全局配置--N--> 使用全局默认配置
     */
    @Override
    public void showLoading() {
        int helpId = getHelpView(Status.LOADING);
        if (checkHelpView(helpId)) {
            showLoading(helpId);
        }else {
            showLoading(getHelpDefaultView(Status.LOADING));
        }
    }

    /**
     * 检查是否已经加载了状态View--N--> 添加View
     */
    @Override
    public void showLoading(int layoutId) {
        View loadingView = getViewFromMap(Status.LOADING);
        if (!checkViewMap(loadingView)) {
            loadingView = mInflate.inflate(layoutId, this);
            addViewToMap(Status.LOADING, loadingView);

            addView(loadingView, DEFAULT_PARAMS);
        }
        showStatusView(loadingView);
    }

    @Override
    public void showEmpty() {
        int helpId = getHelpView(Status.EMPTY);
        if (checkHelpView(helpId)) {
            showEmpty(helpId);
        }else {
            showEmpty(getHelpDefaultView(Status.EMPTY));
        }
    }

    @Override
    public void showEmpty(int layoutId) {
        View emptyView = getViewFromMap(Status.EMPTY);
        if (!checkViewMap(emptyView)) {
            emptyView = mInflate.inflate(layoutId, this);
            addViewToMap(Status.EMPTY, emptyView);

            addView(emptyView, DEFAULT_PARAMS);
        }
        showStatusView(emptyView);
    }

    @Override
    public void showErrNet() {
        int helpId = getHelpView(Status.ERR_NET);
        if (checkHelpView(helpId)) {
            showErrNet(helpId);
        }else {
            showErrNet(getHelpDefaultView(Status.ERR_NET));
        }
    }

    @Override
    public void showErrNet(int layoutId) {
        View errNetView = getViewFromMap(Status.ERR_NET);
        if (!checkViewMap(errNetView)) {
            errNetView = mInflate.inflate(layoutId, this);
            addViewToMap(Status.ERR_NET, errNetView);

            addView(errNetView, DEFAULT_PARAMS);
        }
        showStatusView(errNetView);
    }

    @Override
    public void showError() {
        int helpId = getHelpView(Status.ERROR);
        if (checkHelpView(helpId)) {
            showError(helpId);
        }else {
            showError(getHelpDefaultView(Status.ERROR));
        }
    }

    @Override
    public void showError(int layoutId) {
        View errorView = getViewFromMap(Status.ERROR);
        if (!checkViewMap(errorView)) {
            errorView = mInflate.inflate(layoutId, this);
            addViewToMap(Status.ERROR, errorView);

            addView(errorView, DEFAULT_PARAMS);
        }
        showStatusView(errorView);
    }

    @Override
    public void showSuccess() {
        int helpId = getHelpView(Status.SUCCESS);
        if (checkHelpView(helpId)) {
            showSuccess(helpId);
        }else {
            showSuccess(getHelpDefaultView(Status.SUCCESS));
        }
    }

    @Override
    public void showSuccess(int layoutId) {
        View successView = getViewFromMap(Status.SUCCESS);
        if (!checkViewMap(successView)) {
            successView = mInflate.inflate(layoutId, this);
            addViewToMap(Status.SUCCESS, successView);

            addView(successView, DEFAULT_PARAMS);
        }
        showStatusView(successView);
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<---status---end---<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 是否有指定的全局状态view_id
     */
    private int getHelpView(@Status String status){
        return MultipleHelper.getHelper().getView(status);
    }

    private int getHelpDefaultView(@Status String status){
        return MultipleHelper.getHelper().getDefault(status);
    }

    private boolean checkHelpView(int getHelpView){
        if (getHelpView == 0) {
            return false;
        }
        return true;
    }

    private void addViewToMap(@Status String status, View statusView){
        statusViewMap.put(status, statusView);
    }

    private View getViewFromMap(@Status String status){
        return statusViewMap.get(status);
    }

    private boolean checkViewMap(View status){
        if (status == null) {
            return false;
        }
        return true;
    }


}
