package com.lu.beauty.designer;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.lu.beauty.R;
import com.lu.beauty.base.CommonViewHolder;
import com.lu.beauty.bean.DesignerHeadlineBean;

import java.util.ArrayList;

/**
 * Created by GuoXuanYu on 16/11/30.
 */

public class PopAdapter  extends RecyclerView.Adapter<CommonViewHolder>{
    private ArrayList<DesignerHeadlineBean.DataBean.CategoriesBean.SubCategoriesBean> arrayList;
    private DesignerClickListener designerClickListner;

    public PopAdapter(DesignerClickListener designerClickListner) {
        this.designerClickListner = designerClickListner;
    }

    public void setArrayList(ArrayList<DesignerHeadlineBean.DataBean.CategoriesBean.SubCategoriesBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonViewHolder.getViewHolder(parent,R.layout.designer_pop_item);
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, final int position) {
        holder.setText(R.id.design_pop_itembtn,arrayList.get(position).getName());
        if (arrayList.get(position).getSelect() == 1){
            holder.setTextBackground(R.id.design_pop_itembtn,R.drawable.shape_pop_text);
            holder.setTextColor(R.id.design_pop_itembtn, Color.BLACK);
        }else if (arrayList.get(position).getSelect() == 0){
            holder.setTextColor(R.id.design_pop_itembtn, Color.WHITE);
            holder.setTextBackground(R.id.design_pop_itembtn,R.drawable.shape_pop_textno);
        }
        holder.setItemClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                designerClickListner.popItemClick(arrayList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }
}
