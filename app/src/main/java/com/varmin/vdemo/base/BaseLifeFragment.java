package com.varmin.vdemo.base;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseLifeFragment extends Fragment {
    public String TAG = "BaseLifeFragment";
    private boolean mIsLog = true;
    private boolean mIsAll = false;
    private Context mContext;
    private FragmentActivity mActivity;
    private Unbinder unbinder;
    private boolean isTag;


    public void isLog(boolean isLog) {
        isLog(isLog, true);
    }
    public void isAll(boolean isAll) {
        isLog(true, isAll);
    }
    public void isLog(boolean isLog, boolean isAll) {
        this.mIsLog = isLog;
        this.mIsAll = isAll;
    }
    public void setTAG(String tag){
        isTag = true;
        this.TAG = tag;
    }

    private BaseLifeViewModel mViewModel;

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>---life---begin--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Override
    public void onAttach(Context context) {
        if (!isTag)TAG = getClass().getSimpleName();
        if (mIsLog) Log.d(TAG, "onAttach: ----------------begin");
        super.onAttach(context);
        this.mContext = context;
        this.mActivity = getActivity();
        if (mIsLog && mIsAll) Log.d(TAG, "onAttach: -------end");
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (mIsLog) Log.d(TAG, "onCreate: ----------------begin");
        super.onCreate(savedInstanceState);
        if (mIsLog && mIsAll) Log.d(TAG, "onCreate: -------end");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (mIsLog) Log.d(TAG, "onCreateView: ----------------");
        View mView = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, mView);

        initView(mView);

        Bundle bundle = getArguments();
        if (bundle == null) bundle = new Bundle();
        parseArguments(bundle);
        initData(savedInstanceState);

        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if (mIsLog) Log.d(TAG, "onActivityCreated: ----------------begin");
        super.onActivityCreated(savedInstanceState);
        if (mIsLog && mIsAll) Log.d(TAG, "onActivityCreated: -------end");
        mViewModel = ViewModelProviders.of(this).get(BaseLifeViewModel.class);
        // TODO: Use the ViewModel
    }


    @Override
    public void onStart() {
        if (mIsLog) Log.d(TAG, "onStart: ----------------begin");
        super.onStart();
        if (mIsLog && mIsAll) Log.d(TAG, "onStart: -------end");
    }


    /**
     * 并不属于声明周期
     * View的恢复，不是Fragment的恢复
     */
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        if (mIsLog) Log.w(TAG, "onViewStateRestored: ----------------begin, "+hashCode());
        super.onViewStateRestored(savedInstanceState);
        if (mIsLog && mIsAll) Log.w(TAG, "onViewStateRestored: -------end");
    }



    @Override
    public void onResume() {
        if (mIsLog) Log.d(TAG, "onResume: ----------------begin");
        super.onResume();
        if (mIsLog && mIsAll) Log.d(TAG, "onResume: -------end");
    }

    @Override
    public void onPause() {
        if (mIsLog) Log.d(TAG, "onPause: ----------------begin");
        super.onPause();
        if (mIsLog && mIsAll) Log.d(TAG, "onPause: -------end");
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mIsLog) Log.w(TAG, "onSaveInstanceState: ----------------begin");
        super.onSaveInstanceState(outState);
        if (mIsLog && mIsAll) Log.w(TAG, "onSaveInstanceState: -------end");
    }

    @Override
    public void onStop() {
        if (mIsLog) Log.d(TAG, "onStop: ----------------begin");
        super.onStop();
        if (mIsLog && mIsAll) Log.d(TAG, "onStop: -------end");
    }

    @Override
    public void onDestroyView() {
        if (mIsLog) Log.d(TAG, "onDestroyView: ----------------begin");
        super.onDestroyView();
        if (mIsLog && mIsAll) Log.d(TAG, "onDestroyView: -------end");
    }

    @Override
    public void onDestroy() {
        if (mIsLog) Log.d(TAG, "onDestroy: ----------------begin");
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (mIsLog && mIsAll) Log.d(TAG, "onDestroy: -------end");
    }

    @Override
    public void onDetach() {
        if (mIsLog) Log.d(TAG, "onDetach: ----------------begin");
        super.onDetach();
        if (mIsLog && mIsAll) Log.d(TAG, "onDetach: -------end");
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<---life---end---<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    public abstract void parseArguments(Bundle args);
    public abstract int getLayoutId();
    public void initView(View mView){}
    public void initData(Bundle savedInstanceState){}

    public Context getMContext() {
        return mContext;
    }

    public FragmentActivity getMActivity() {
        return mActivity;
    }

}
