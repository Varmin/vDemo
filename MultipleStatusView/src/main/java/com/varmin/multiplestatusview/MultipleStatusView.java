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
     * TODO ： contentView 时机？
     */
    private void initView() {
        mInflate = LayoutInflater.from(getContext());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }




    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>---status---begin--->>>>>>>>>>>>>>>>>>>>>>>>>>
    @Override
    public void showContentView() {

    }

    @Override
    public void showStatusView(View view) {
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            childView.setVisibility(childView == view ? VISIBLE:GONE);
        }
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
     * 检查是否已经加载了状态View
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

    }

    @Override
    public void showEmpty(int layoutId) {

    }

    @Override
    public void showErrNet() {

    }

    @Override
    public void showErrNet(int layoutId) {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showError(int layoutId) {

    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void showSuccess(int layoutId) {

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
