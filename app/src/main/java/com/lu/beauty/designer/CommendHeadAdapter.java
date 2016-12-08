package com.lu.beauty.designer;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.lu.beauty.base.CommonViewHolder;

/**
 * Created by GuoXuanYu on 16/11/26.
 */

public class CommendHeadAdapter extends RecyclerView.Adapter {
    private  RecyclerView.Adapter innerAdapter;
    private static  final  int BASE_TYPE = 100000;
    private SparseArray<View> headViews;

    public CommendHeadAdapter(RecyclerView.Adapter innerAdapter) {
        this.innerAdapter = innerAdapter;
        headViews = new SparseArray<>();
    }

    public void addHeadView (View headView){

        headViews.put(BASE_TYPE + headViews.size(),headView);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if (headViews.get(viewType) != null){
            holder = CommonViewHolder.getHeadViewHolder(headViews.get(viewType));
        }else {
            holder = innerAdapter.onCreateViewHolder(parent,viewType);
        }
        return holder;
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager){
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;

            // 列数
            final int spanCount = gridLayoutManager.getSpanCount();
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position < headViews.size()){
                        return spanCount;
                    }
                    return 1;
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position >= headViews.size()){
            innerAdapter.onBindViewHolder(holder,position - headViews.size());
        }
    }

    @Override
    public int getItemViewType(int position) {
        // 头布局
        if (position < headViews.size()){
            return BASE_TYPE + position;
        }else {
            return innerAdapter.getItemViewType(position - headViews.size());
        }
    }

    @Override
    public int getItemCount() {
        return  headViews.size()+ innerAdapter.getItemCount();
    }
}
