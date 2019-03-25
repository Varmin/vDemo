package com.varmin.vdemo.recyclerview;

import android.graphics.Rect;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;
import com.varmin.vdemo.recyclerview.adapter.UnPackAdapter;
import com.varmin.vdemo.recyclerview.decration.LinearLayoutColorDivider;

import java.util.ArrayList;
import butterknife.BindView;

public class UnPackActivity extends BaseActivity {

    @BindView(R.id.rv_un_pack_recylerview)
    RecyclerView rvUnPack;
    private UnPackAdapter mUnPackAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_un_pack;
    }

    @Override
    protected void initData() {
        super.initData();

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("content"+i);
        }
        mUnPackAdapter = new UnPackAdapter(list, R.layout.item_common);
        rvUnPack.setLayoutManager(new LinearLayoutManager(this));
        //rvUnPack.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        LinearLayoutColorDivider linearDecoration = new LinearLayoutColorDivider(getResources(), R.color.base_red, R.dimen.margin_top, LinearLayoutManager.VERTICAL);
        rvUnPack.addItemDecoration(linearDecoration);
        rvUnPack.setAdapter(mUnPackAdapter);


        Person per = new Person();
        Rect r = per.rect;
        r.left = 11;
        r.top = 22;
        Log.d(TAG, "initData: "+per.rect+", "+per.rect.hashCode()+", "+r.hashCode());
        per.rect = new Rect(111,222,333,444);
        Log.d(TAG, "initData: "+per.rect+", "+per.rect.hashCode());
    }

    public Rect set(Rect rect){
        rect.set(11,22,33,44);
        return rect;
    }

    public Person setPer(Person person){
        person.name = "setPer";
        return person;
    }

    class Person{
        public String name = "varmin";
        public Rect rect = new Rect(1,2,3,4);
    }
}
