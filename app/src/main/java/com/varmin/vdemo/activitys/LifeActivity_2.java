package com.varmin.vdemo.activitys;

import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;
import com.varmin.vdemo.base.Utils;

import butterknife.OnClick;

public class LifeActivity_2 extends BaseActivity {
    {
        isAll(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_life_2;
    }


    @OnClick(R.id.btn_life_2)
    public void onViewClicked() {
        Utils.startActivity(ActivityMainActivity.class);
    }

    /**
     * 未指定tarify
     * standard、singleTop: 谁启动进入谁
     * singleTask 默认自己的包名栈
     * singleInstarnce 自己一个栈
     *
     *
     *
     * allowTaskReparenting：
     *  App1-->App2 此时App2中的C页面在App1栈中
     *  home
     *  点击App2图标，C页面从App1栈转入到App2栈中
     *
     * singleTask、taskAffinity、allowTaskReparenting：
     *
     *
     */
}
