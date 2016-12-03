package com.lu.beauty.designer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lu.beauty.R;
import com.lu.beauty.base.CommonViewHolder;
import com.lu.beauty.bean.DesignerRecommendBean;

import java.util.ArrayList;

/**
 * Created by GuoXuanYu on 16/11/26.
 */

public class HeadItemAdapter extends RecyclerView.Adapter<CommonViewHolder> {
    private ArrayList<DesignerRecommendBean.DataBean.CategoriesBeanX> arrayList;
    private DesignerClickListner designerClickListner;

    public HeadItemAdapter(DesignerClickListner designerClickListner) {
        this.designerClickListner = designerClickListner;
    }

    public void setArrayList(ArrayList<DesignerRecommendBean.DataBean.CategoriesBeanX> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonViewHolder.getViewHolder(parent, R.layout.designer_header_item);
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, final int position) {
        holder.setButtonText(R.id.designer_header_itembtn,arrayList.get(position).getName());
        holder.setViewClick(R.id.designer_header_itembtn,new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                designerClickListner.HeadItemClick(arrayList.get(position));
                Log.d("HeadItemAdapter", "点击");
                designerClickListner.headItemClick(arrayList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 :arrayList.size();
    }
}
