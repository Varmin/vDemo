package com.varmin.multiplestatusview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
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
 * todo: 下拉刷、自定义动画：（全局、默认、局部）
 */
public class MultipleStatusView extends FrameLayout implements MultipleStatus{
    private static final String TAG = "MultipleStatusView";
    private FrameLayout.LayoutParams DEFAULT_PARAMS = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    private Map<String, View> statusViewMap = new HashMap<>();
    private LayoutInflater mInflate;
    private View mContentView;
    private OnRetryListener mOnRetryListener;
    private boolean mIsFromXml = true;

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
        //判断View初始化：布局或动态添加
        setIsFromXml((attrs == null && defStyleAttr == 0) ? false:true);
        Log.d(TAG, "MultipleStatusView: getCount="+getChildCount());
    }



    private void initAttr() {

    }

    private void initView() {
        mInflate = LayoutInflater.from(getContext());
    }

    /**
     * 判断初始化类型：布局、动态生成
     * @param isFromXml 是否在布局中加载
     */
    private void setIsFromXml(boolean isFromXml){
        this.mIsFromXml = isFromXml;
        Log.d(TAG, "setIsFromXml: "+mIsFromXml);
    }

    /**
     * TODO：
     * 1, 布局添加的contentView, 在onFinishInflate检查
     * 2, 动态添加的contentView + tag,在addView时通过tag确定内容View。（肯定有需求，单独类实现）
     */


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>---type_1:布局添加的contentView, 在onFinishInflate检查---begin--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //解析、添加子View完成，setContentView时即使解析、加载的过程。
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d(TAG, "onFinishInflate: getCount="+getChildCount());
        if(mIsFromXml){
            if (getChildCount() == 0) throw new NoContentViewException();
            if (getChildCount() > 1) throw new MultipleContentViewException();
            mContentView = getChildAt(0);
        }
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<---type_1:布局添加的contentView, 在onFinishInflate检查---end---<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 写入布局的MuiltipleView设置
     */
    private void setContentView(View contentView){
        this.mContentView = contentView;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow: getCount="+getChildCount());
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow: getCount="+getChildCount());
    }


    /**
     * 显示主View，内容
     */
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

    /**
     * 显示状态View
     */
    @Override
    public void showStatusView(View view) {
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            childView.setVisibility(childView == view ? VISIBLE:GONE);
        }
    }

   //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>---statusView显示---begin--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

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

    @Override
    public void setRetryOnclickListener(OnRetryListener onRetryListener) {
        this.mOnRetryListener = onRetryListener;
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
     * 检查是否已经加载了状态View--N--> 添加View，缓存到局部map
     */
    private void showStatusView(@Status final String status, int layoutId){
        View stateView = getViewFromMap(status);
        if (!checkViewMap(stateView)) {

            //返回的stateView是FramLayout！
            stateView = mInflate.inflate(layoutId, this, false);
            addView(stateView, DEFAULT_PARAMS);
            //Log.d(TAG, "showStatusView_1: "+stateView.getLayoutParams());

            //返回的stateView是MultipleStatusView！不是FramLayout
           /* stateView = mInflate.inflate(layoutId, this, true);
            Log.d(TAG, "showStatusView_2: "+stateView.getLayoutParams());*/

            addViewToMap(status, stateView);

            if ((TextUtils.equals(status, Status.ERR_NET) || TextUtils.equals(status, Status.ERROR))
                    && mOnRetryListener != null) {
                    stateView.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mOnRetryListener.onRetryClick(status, v);
                        }
                    });
            }
        }
        showStatusView(stateView);
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<---statusView显示---end---<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>---获取全局、默认配置---begin--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    /**
     * 指定全局配置
     */
    private int getHelpView(@Status String status){
        return MultipleHelper.getHelper().getView(status);
    }

    /**
     * 默认全局配置
     */
    private int getHelpDefaultView(@Status String status){
        return MultipleHelper.getHelper().getDefault(status);
    }

    /**
     * 检查全局配置是否有效
     */
    private boolean checkHelpView(int getHelpView){
        return getHelpView == MultipleHelper.DEFAULT_NO_VALUE ? false:true;
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<---获取全局、默认配置---end---<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>---获取局部缓存配置---begin--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    /**
     * 懒加载
     * 无论是全局配置，还是单独的自定义statusView配置，加载过后缓存到map中。
     */
    private void addViewToMap(@Status String status, View statusView){
        statusViewMap.put(status, statusView);
    }

    /**
     * 直接取加载过的View
     */
    private View getViewFromMap(@Status String status){
        return statusViewMap.get(status);
    }

    private boolean checkViewMap(View status){
        return status == null ? false:true;
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<---获取局部缓存配置---end---<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

}
