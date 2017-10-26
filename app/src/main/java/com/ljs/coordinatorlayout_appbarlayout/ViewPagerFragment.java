package com.ljs.coordinatorlayout_appbarlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ljs.recyclerview.DividerItemGridLayout;
import com.ljs.core.IObject;
import com.ljs.recyclerview.MyRecyclerViewAdpter;
import com.ljs.sumery.R;
import com.ljs.utils.ToastManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 暮雨而歌 on 2017/10/13.
 */

public class ViewPagerFragment extends Fragment{
    private List<IObject> mList;
    private MyRecyclerViewAdpter mMyRecyclerViewAdpter;
    private RecyclerView mRecyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_tablayout_viewpager,
                null,false);
        mRecyclerView = view.findViewById(R.id.recyclerView);

//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
//        mRecyclerView.addItemDecoration(new DividerItemGridLayout.Builder(getContext())
//                .size(4)
//                .color(R.color.color_gray)
//                .build());

        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        mRecyclerView.addItemDecoration(new DividerItemGridLayout.Builder(getContext())
                .size(4)
                .color(R.color.color_gray)
                .build());

//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecyclerView.addItemDecoration(new DividerItemLinearLayoutDecoration(getActivity(),
//                LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mMyRecyclerViewAdpter = new MyRecyclerViewAdpter(getDataList())
                .setmOnItemClickListener(new MyRecyclerViewAdpter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        ToastManager.getInstance().show("onItemClick"+view.getClass()+position);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        ToastManager.getInstance().show("onItemLongClick"+view.getClass()+position);
                    }
                }));
        return view;
    }

    private List<IObject> getDataList(){
        mList = new ArrayList<>();
        for(int i = 0;i<30;i++){
            IObject io = new IObject(i);
            mList.add(io);
        }
        return mList;
    }

    public void addData(int position,IObject io){
        mMyRecyclerViewAdpter.addData(position,io);
    }

    public void deleteData(int position){
        mMyRecyclerViewAdpter.deleteData(position);
    }

}
