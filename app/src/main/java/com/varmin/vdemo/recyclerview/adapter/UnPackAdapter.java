package com.varmin.vdemo.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.varmin.vdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuangYang
 * on 2019/3/16  15:41.
 * 文件描述：未封装情况下的功能实现
 */
public class UnPackAdapter extends RecyclerView.Adapter<UnPackAdapter.UnPackViewHolder> {
    private static final String TAG = "UnPackAdapter";
    private List<String> mDatas;
    private int mLayoutId;
    private int mCount;

    public UnPackAdapter(List<String> list, int layoutId){
        this.mDatas = list;
        if (mDatas == null) mDatas = new ArrayList<>();
        this.mLayoutId = layoutId;
    }

    @Override
    public UnPackViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: --------");

        View itemView = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
        final UnPackViewHolder viewHolder = new UnPackViewHolder(itemView, mCount++);

        //在onCreate中设置监听，itemView-holder-listener，一一对应
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(parent.getContext(), "item_click_"+viewHolder.getLayoutPosition(), Toast.LENGTH_SHORT).show();
            }
        });
        itemView.findViewById(R.id.tv_common_recycler_center).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(parent.getContext(), "item_child_click_"+viewHolder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }
        });
        return viewHolder;
    }

    /**
     * 复用holder
     */
    @Override
    public void onBindViewHolder(UnPackViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: "+position);
        holder.tvLeft.setText(""+position);
        holder.tvCenter.setText(mDatas.get(position));
        holder.tvRight.setText("VH_Cc:"+holder.count);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    /**
     * 保存ItemView和子View，不用再findView
     */
    class UnPackViewHolder extends RecyclerView.ViewHolder{
        public int count;
        public TextView tvLeft;
        public TextView tvCenter;
        public TextView tvRight;

        public UnPackViewHolder(View itemView, int count) {
            super(itemView);
            this.count = count;
            itemView.setTag(count);
            Log.d(TAG, "UnPackViewHolder: "+getLayoutPosition()+", "+getOldPosition()+", "+getAdapterPosition()+", "+getItemCount()+", "+getItemId());
            tvLeft = itemView.findViewById(R.id.tv_common_recycler_left);
            tvCenter = itemView.findViewById(R.id.tv_common_recycler_center);
            tvRight = itemView.findViewById(R.id.tv_common_recycler_right);
        }
    }

    public List<String> getData(){
        return mDatas;
    }
}
