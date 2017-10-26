package com.ljs.collapsing_toolbarlayout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.ljs.recyclerview.MyRecyclerViewAdpter;
import com.ljs.core.IObject;
import com.ljs.sumery.R;

import java.util.ArrayList;
import java.util.List;

import afinal.FinalActivity;
import afinal.annotation.view.ViewInject;

/**
 * Created by 暮雨而歌 on 2017/10/17.
 */

public class CollapsingToolbarLayoutActivityDemo extends AppCompatActivity{
    @ViewInject(idString = "toolbar_layout") CollapsingToolbarLayout mCollapsingToolbarLayout;
    @ViewInject(idString = "detail_img")     ImageView mImageViewDetail;
    @ViewInject(idString = "toolbar")        Toolbar mToolbar;
    @ViewInject(idString = "app_bar")        AppBarLayout mAppBarLayout;
    @ViewInject(idString = "recyclerView")   RecyclerView mRecyclerView;
    @ViewInject(idString = "nestedScrollView")
    NestedScrollView mNestedScrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar);

        initView();
    }

    private void initView() {
        FinalActivity.initInjectedView(this);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });

        mCollapsingToolbarLayout.setTitle("CollapsingToolbarLayout");
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.YELLOW);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(new StaggeredAdapter(getDataList()));
    }

    private List<IObject> getDataList(){
        List<IObject> mList = new ArrayList<>();
        for(int i = 0;i<30;i++){
            IObject io = new IObject(i);
            mList.add(io);
        }
        return mList;
    }

    public static class StaggeredAdapter extends MyRecyclerViewAdpter{

        public StaggeredAdapter(List<IObject> list) {
            super(list);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            //瀑布流设置动态高度
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) holder.tv.getLayoutParams();
            int height = (int) (lp.height + Math.random() * 300);
            lp.height = height;
            holder.tv.setLayoutParams(lp);

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

    }
}
