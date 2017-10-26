package com.ljs.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljs.core.IObject;
import com.ljs.sumery.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 暮雨而歌 on 2017/10/16.
 */

public class MyRecyclerViewAdpter extends RecyclerView.Adapter<MyRecyclerViewAdpter.MyViewHolder>{
    protected List<IObject> mList;
    protected OnItemClickListener mOnItemClickListener;
    public MyRecyclerViewAdpter(List<IObject> list){
        this.mList = list;
    }

    public MyRecyclerViewAdpter setmOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
        return this;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.tablayout_recycler_layout,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        //瀑布流设置动态高度
//        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) holder.tv.getLayoutParams();
//        int height = (int) (lp.height + Math.random() * 300);
//        lp.height = height;
//        holder.tv.setLayoutParams(lp);

        holder.tv.setText(mList.get(position).id+"");

        if(mOnItemClickListener != null){
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.tv,pos);
                }
            });

            holder.tv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.tv,pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(mList == null) return 0;
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tvRecycler);
        }
    }

    public void addData(int position,IObject iObject){
        if(mList == null){
            mList = new ArrayList<>();
        }
        mList.add(position,iObject);
        notifyDataSetChanged();
    }

    public void deleteData(int position){
        if(mList == null || mList.size() == 0){
            return;
        }
        mList.remove(position);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }
}
