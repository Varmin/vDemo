package com.varmin.vdemo.recyclerview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;
import com.varmin.vdemo.recyclerview.adapter.UnPackAdapter;
import com.varmin.vdemo.recyclerview.animator.CustomItemAnimator;
import com.varmin.vdemo.recyclerview.decration.LinearLayoutColorDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
        rvUnPack.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        //rvUnPack.addItemDecoration(new LinearLayoutColorDivider(getResources(), R.color.base_red, R.dimen.margin_top, LinearLayoutManager.VERTICAL));
        rvUnPack.setItemAnimator(new CustomItemAnimator());
        rvUnPack.setAdapter(mUnPackAdapter);

    }

    int addIndex = 0;
    @OnClick({R.id.btn_unpack_add, R.id.btn_unpack_remove})
    public void onViewClicked(View view){
        switch (view.getId()) {
            case R.id.btn_unpack_add:
                addIndex++;
                mUnPackAdapter.getData().add(1, "add_"+addIndex);
                mUnPackAdapter.notifyItemInserted(1);
                mUnPackAdapter.notifyItemRangeChanged(1, mUnPackAdapter.getData().size());
                Log.d(TAG, "onViewClicked: add, "+addIndex+", "+mUnPackAdapter.getData().size());
                break;
            case R.id.btn_unpack_remove:
                mUnPackAdapter.getData().remove(1);
                mUnPackAdapter.notifyItemRemoved(1);
                mUnPackAdapter.notifyItemRangeChanged(1, mUnPackAdapter.getData().size());
                Log.d(TAG, "onViewClicked: remove, "+mUnPackAdapter.getData().size());
                break;
        }
    }

    class DiffCallback extends DiffUtil.Callback{
        public DiffCallback(List<String> oldList, List<String> newList){

        }
        @Override
        public int getOldListSize() {
            return 0;
        }

        @Override
        public int getNewListSize() {
            return 0;
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return false;
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            return super.getChangePayload(oldItemPosition, newItemPosition);
        }
    }

    public static class IA3 extends DefaultItemAnimator{
        private ArrayList<RecyclerView.ViewHolder> mPendingAdditions = new ArrayList<>();
        @Override
        public boolean animateAdd(RecyclerView.ViewHolder holder) {
            //return super.animateAdd(holder);
            mPendingAdditions.add(holder);
            return true;
        }
    }

    public static class IA2 extends SimpleItemAnimator{

        @Override
        public boolean animateRemove(RecyclerView.ViewHolder holder) {
            return false;
        }

        @Override
        public boolean animateAdd(RecyclerView.ViewHolder holder) {
            return false;
        }

        @Override
        public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
            return false;
        }

        @Override
        public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
            return false;
        }

        @Override
        public void runPendingAnimations() {

        }

        @Override
        public void endAnimation(RecyclerView.ViewHolder item) {

        }

        @Override
        public void endAnimations() {

        }

        @Override
        public boolean isRunning() {
            return false;
        }
    }

    public static class IA1 extends RecyclerView.ItemAnimator{

        @Override
        public boolean animateDisappearance(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull ItemHolderInfo preLayoutInfo, @Nullable ItemHolderInfo postLayoutInfo) {
            return false;
        }

        @Override
        public boolean animateAppearance(@NonNull RecyclerView.ViewHolder viewHolder, @Nullable ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
            return false;
        }

        @Override
        public boolean animatePersistence(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
            return false;
        }

        @Override
        public boolean animateChange(@NonNull RecyclerView.ViewHolder oldHolder, @NonNull RecyclerView.ViewHolder newHolder, @NonNull ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
            return false;
        }

        @Override
        public void runPendingAnimations() {

        }

        @Override
        public void endAnimation(RecyclerView.ViewHolder item) {

        }

        @Override
        public void endAnimations() {

        }

        @Override
        public boolean isRunning() {
            return false;
        }
    }

}
