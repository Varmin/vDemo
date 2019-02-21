package com.varmin.vdemo.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 1，commit异步
 * 2，返回键作用在Activity，加入回退栈返回键作用在栈上
 * 3，hide/show：不影响生命周期。如果已经hide的Fragment，再add的话不显示，需再设置show。
 * 4，detach从View中删除，从add队列中删除，但还可以通过manager找到
 * 5，addBackStack/popBackStack: 操作的是每次commit，一个或多个fragment。
 */
public class ManaFragmentActivity extends BaseActivity implements TransArgumentsListener{
    private Fragment_1 fragment_1;

    @BindView(R.id.fl_fg_container)
    FrameLayout flFgContainer;
    @BindView(R.id.tv_frag_list)
    TextView tvFragsInfo;
    int count;
    private StringBuilder mFragsInfoBuilder;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mana_fragment;
    }

    @Override
    protected void initData() {
        super.initData();
        //add("init_add");
    }

    @OnClick({R.id.btn_fg_add, R.id.btn_fg_list, R.id.btn_fg_remove, R.id.btn_fg_replace
            , R.id.btn_fg_hide, R.id.btn_fg_show, R.id.btn_add_back_stack, R.id.btn_pop_back_stack
            , R.id.btn_fg_attach, R.id.btn_fg_detach, R.id.btn_fg_back_stack_count
            , R.id.btn_fg_act_to_frag_argus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_fg_list:
                getFragList();
                break;
            case R.id.btn_fg_back_stack_count:
                getBackStackList();
                break;
            case R.id.btn_fg_add:
                add("click_add");
                break;
            case R.id.btn_fg_remove:
                remove();
                break;
            case R.id.btn_fg_replace:
                replace();
                break;
            case R.id.btn_fg_show:
                show();
                break;
            case R.id.btn_fg_hide:
                hide();
                break;
            case R.id.btn_add_back_stack:
                addBackStack();
                break;
            case R.id.btn_pop_back_stack:
                popBackStack();
                break;
            case R.id.btn_fg_detach:
                detach();
                break;
            case R.id.btn_fg_attach:
                attach();
                break;
            case R.id.btn_fg_act_to_frag_argus:
                transArgusToFragment();
                break;
        }
    }


    private void attach() {
        getFgList();
        Fragment initFragment = getSupportFragmentManager().findFragmentByTag("xml_init_fragment");
        getSupportFragmentManager().beginTransaction()
                .attach(initFragment)
                .addToBackStack("init_add_attach")
                .commit();
        getFgList();
    }

    private void detach() {
        getFgList();
        Fragment initFragment = getSupportFragmentManager().findFragmentByTag("xml_init_fragment");
        getSupportFragmentManager().beginTransaction()
                .detach(initFragment)
                .addToBackStack("init_add_detach")
                .commit();
        getFgList();
    }

    private void popBackStack() {
        getFgList();
        //异步
        getSupportFragmentManager().popBackStack();
        getFgList();
    }


    private void addBackStack() {
        getFgList();
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.add(R.id.fl_fg_container, Fragment_1.newInstance("add_back_stack_" + ++count), "addBS_" + count);
        trans.addToBackStack("add_back_stack_" + count);
        trans.commit();
        getFgList();
    }


    private void replace() {
        getFgList();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_fg_container, Fragment_1.newInstance("replace_add"), "repalce_tag")
                .commitNow();
        getFgList();
    }


    private String fgStatus(Fragment fragment) {
        StringBuilder status = new StringBuilder();
        status.append("\n").append("isHidden:" + fragment.isHidden())
                .append("\n").append("isAdded:" + fragment.isAdded())
                .append("\n").append("isDetached:" + fragment.isDetached())
                .append("\n").append("" + fragment.isVisible())
                .append("\n").append("" + fragment.isStateSaved());
        tvFragsInfo.setText(status.toString());
        return status.toString();
    }


    private void getFragList() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        Log.d(TAG, "getFragList: " + fragments.size());
        mFragsInfoBuilder.append("getFragList: " + fragments.size()).append("\n");
        for (Fragment fragment : fragments) {
            Log.d(TAG, "getFragList: " + fragment.getTag());
            mFragsInfoBuilder.append(fragment.getTag()).append("\n");
        }
    }
    private void getBackStackList() {
        int bsCount = getSupportFragmentManager().getBackStackEntryCount();
        Log.d(TAG, "getBackStackList: size="+bsCount);
        mFragsInfoBuilder.append("getBackStackList: size="+bsCount).append("\n");
        for (int i = 0; i < bsCount; i++) {
            FragmentManager.BackStackEntry entry = getSupportFragmentManager().getBackStackEntryAt(i);
            Log.d(TAG, "getBackStackList: "+entry.getName()+", "+entry.getBreadCrumbTitle());
            mFragsInfoBuilder.append(entry.getName()).append("\n");
        }
    }

    private void show() {
        if (fragment_1 == null) {
            Log.d(TAG, "show: newInstance");
            fragment_1 = Fragment_1.newInstance();
        }
        Log.d(TAG, "show: " + fgStatus(fragment_1));
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        if (!fragment_1.isAdded()) {
            trans.add(R.id.fl_fg_container, fragment_1, "show_add_" + (++count));
        }
        if (fragment_1.isHidden()) {
            trans.show(fragment_1);
        }
        trans.commitNow();
        Log.d(TAG, "show: " + fgStatus(fragment_1));
        getFgList();
    }

    private void hide() {
        if (fragment_1 != null) {
            Log.d(TAG, "hide: " + fgStatus(fragment_1));
            if (!fragment_1.isHidden()) {
                getSupportFragmentManager().beginTransaction()
                        .hide(fragment_1)
                        .commitNow();
                getFgList();
            }
            Log.d(TAG, "hide: " + fgStatus(fragment_1));
        }
    }

    private void remove() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        getFgList();
        if (fragments.size() > 0) {
            count--;
            getSupportFragmentManager().beginTransaction()
                    .remove(fragments.get(fragments.size() - 1))
                    .commitNow();
            getFgList();
        }
    }

    private void add(String tag) {
        tag = tag + "_" + ++count;
        Log.d(TAG, "add: " + tag);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_fg_container, Fragment_1.newInstance(tag), tag)
                .commit();
        getFgList();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean result = super.onKeyDown(keyCode, event);
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            getFgList();
        }
        return result;
    }

    private void getFgList(){
        flFgContainer.postDelayed(new Runnable() {
            @Override
            public void run() {
                mFragsInfoBuilder = new StringBuilder();
                getFragList();
                getBackStackList();
                tvFragsInfo.setText(mFragsInfoBuilder.toString());
            }
        }, 500);

    }

    /**
     * Fragment传递参数给Activity
     */
    @Override
    public void setArgus(String argus) {
        Toast.makeText(mActivity, argus, Toast.LENGTH_SHORT).show();
    }

    /**
     * Act传递参数到Fragment
     */
    int argCc = 0;
    private void transArgusToFragment() {
        List<Fragment> frags = getSupportFragmentManager().getFragments();
        if (frags.size() > 0) {
            Fragment_1 fragment = (Fragment_1)frags.get(frags.size() - 1);
            fragment.setText("Act -> Frag Argus "+ ++argCc);
        }
    }




}
