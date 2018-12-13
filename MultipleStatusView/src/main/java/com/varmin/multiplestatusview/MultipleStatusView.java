package com.varmin.multiplestatusview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HuangYang
 * on 2018/12/12  15:57.
 * 文件描述：
 * todo: 下拉刷新、点击重试、自定义动画
 */
public class MultipleStatusView extends FrameLayout implements MultipleStatus{
    private static final String TAG = "MultipleStatusView";
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

        if (getChildCount() == 0) throw new NoContentViewException();
        if (getChildCount() > 1) throw new MultipleContentViewException();

        mContentView = getChildAt(0);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }




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

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>---status---begin--->>>>>>>>>>>>>>>>>>>>>>>>>>
    @Override
    public void showRefresh() {
        int helpId = getHelpView(Status.REFRESH);
        if (checkHelpView(helpId)) {
            showRefresh(helpId);
        }else {
            showRefresh(getHelpDefaultView(Status.REFRESH));
        }
    }

    @Override
    public void showRefresh(int layoutId) {
        View refreshView = getViewFromMap(Status.REFRESH);
        if (!checkViewMap(refreshView)) {
            refreshView = mInflate.inflate(layoutId, this, false);
            addViewToMap(Status.REFRESH, refreshView);

            addView(refreshView, DEFAULT_PARAMS);
        }
        //todo 不隐藏contentView，显示refresh
        showStatusView(refreshView);
    }

    @Override
    public void showLoading() {
        showStatusView(Status.LOADING);
    }

    @Override
    public void showLoading(int layoutId) {
        showStatusView(Status.LOADING, layoutId);
    }

    @Override
    public void showEmpty() {
        showStatusView(Status.EMPTY);
    }

    @Override
    public void showEmpty(int layoutId) {
        showStatusView(Status.EMPTY, layoutId);
    }

    @Override
    public void showErrNet() {
        showStatusView(Status.ERR_NET);
    }

    @Override
    public void showErrNet(int layoutId) {
        showStatusView(Status.ERR_NET, layoutId);
    }

    @Override
    public void showError() {
        showStatusView(Status.ERROR);
    }

    @Override
    public void showError(int layoutId) {
        showStatusView(Status.ERROR, layoutId);
    }


    @Override
    public void showSuccess() {
        showStatusView(Status.SUCCESS);
    }

    @Override
    public void showSuccess(int layoutId) {
        showStatusView(Status.SUCCESS, layoutId);
    }

    /**
     * 检查全局配置--N--> 使用全局默认配置
     */
    private void showStatusView(@Status String state){
        int helpId = getHelpView(state);
        if (checkHelpView(helpId)) {
            showStatusView(state, helpId);
        }else {
            showStatusView(state, getHelpDefaultView(state));
        }
    }
    /**
     * 检查是否已经加载了状态View--N--> 添加View
     */
    private void showStatusView(@Status String status,  int layoutId){
        View stateView = getViewFromMap(status);
        if (!checkViewMap(stateView)) {
            addViewToMap(status, stateView);
            //TODO 为什么只有这种方式可以？root和attach到底是什么关系？
            stateView = mInflate.inflate(layoutId, this, false);
            addView(stateView, DEFAULT_PARAMS);

//            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) stateView.getLayoutParams();
//            Log.e(TAG, "showStatusView: "+params.width+", "+params.height+", "+stateView.getMeasuredWidth()+", "+stateView.getMeasuredHeight());
        }
        showStatusView(stateView);
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
        return getHelpView == MultipleHelper.DEFAULT_NO_VALUE ? false:true;
    }

    private void addViewToMap(@Status String status, View statusView){
        statusViewMap.put(status, statusView);
    }

    private View getViewFromMap(@Status String status){
        return statusViewMap.get(status);
    }

    private boolean checkViewMap(View status){
        return status == null ? false:true;
    }


}
