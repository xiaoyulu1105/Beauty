package com.lu.beauty.designer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.lu.beauty.R;
import com.lu.beauty.base.CommonViewHolder;
import com.lu.beauty.bean.DesignerRecommendBean;
import com.lu.beauty.bean.DesignerRecommendItemBean;

import java.util.ArrayList;

/**
 * Created by GuoXuanYu on 16/11/24.
 */

public class DesignerAllAdapter extends RecyclerView.Adapter<CommonViewHolder> {
    private ArrayList<DesignerRecommendBean.DataBean.DesignersBean> arrayList;
    private DesignerClickListner designerClickListner;

    public DesignerAllAdapter(DesignerClickListner designerClickListner) {
        this.designerClickListner = designerClickListner;
    }

    public void setArrayList(ArrayList<DesignerRecommendBean.DataBean.DesignersBean> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return CommonViewHolder.getViewHolder(parent, R.layout.fragment_designer_item);
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, final int position) {
        holder.setText(R.id.design_item_name,arrayList.get(position).getName());
        holder.setText(R.id.design_item_label,arrayList.get(position).getLabel());
        holder.setCircleImage(R.id.design_item_avatar,arrayList.get(position).getAvatar_url());
        holder.setImage(R.id.design_item_images,arrayList.get(position).getRecommend_images().get(0));
        if (arrayList.get(position).getType() == 1) {
            holder.setTextVisibale(R.id.design_item_follow);
            holder.setText(R.id.design_item_follow, arrayList.get(position).getFollow_num() + " 关注");
        }
        holder.setItemClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                designerClickListner.allAdapterItemClick(arrayList.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList == null? 0 : arrayList.size();
    }
}
