package com.lu.beauty.designer.threadactivity;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.lu.beauty.R;
import com.lu.beauty.base.CommonViewHolder;
import com.lu.beauty.bean.DesignerSecondProductsBean;

import java.util.ArrayList;

/**
 * Created by GuoXuanYu on 16/12/7.
 */

public class DesignerProductsAdapter extends RecyclerView.Adapter<CommonViewHolder> {
    DesignerSecondProductsBean bean;

    public void setBean(DesignerSecondProductsBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonViewHolder.getViewHolder(parent,R.layout.activity_designer_products_item);
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        holder.setImage(R.id.activity_designer_products_iv,bean.getData().getProducts().get(position).getCover_images().get(0));
    }

    @Override
    public int getItemCount() {
        return bean == null ? 0 :bean.getData().getProducts().size();
    }
}
