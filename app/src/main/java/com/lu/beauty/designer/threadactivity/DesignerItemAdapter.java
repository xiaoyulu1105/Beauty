package com.lu.beauty.designer.threadactivity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.lu.beauty.R;
import com.lu.beauty.base.CommonViewHolder;
import com.lu.beauty.bean.DesignerSecondBasicBean;

import java.util.ArrayList;

/**
 * Created by GuoXuanYu on 16/12/9.
 */

public class DesignerItemAdapter extends RecyclerView.Adapter<CommonViewHolder> {
    ArrayList<DesignerSecondBasicBean.DataBean.CategoriesBean> arrayList;
    DesignerItemListener designerItemListener;

    public DesignerItemAdapter(DesignerItemListener designerItemListener) {
        this.designerItemListener = designerItemListener;
    }

    public void setArrayList(ArrayList<DesignerSecondBasicBean.DataBean.CategoriesBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonViewHolder.getViewHolder(parent, R.layout.designer_item_type);
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, final int position) {
        holder.setText(R.id.designer_item_type_tv,arrayList.get(position).getName());
        holder.setItemClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                designerItemListener.typeClick(arrayList.get(position).getId(),arrayList.get(position).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }
}
