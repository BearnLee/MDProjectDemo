package com.ljs.coordinatorlayout_appbarlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ljs.recyclerview.DividerItemGridLayout;
import com.ljs.recyclerview.DividerItemLinearLayoutDecoration;
import com.ljs.sumery.IObject;
import com.ljs.recyclerview.MyRecyclerViewAdpter;
import com.ljs.sumery.R;
import com.ljs.utils.ToastManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 暮雨而歌 on 2017/10/13.
 */

public class ViewPagerFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_tablayout_viewpager,
                null,false);
        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerView);

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
        mRecyclerView.setAdapter(new MyRecyclerViewAdpter(getDataList())
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
        List<IObject> list = new ArrayList<>();
        for(int i = 0;i<30;i++){
            IObject io = new IObject();
            io.id = i;
            list.add(io);
        }
        return list;
    }
}
