package com.lu.beauty.designer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;

import android.util.Log;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lu.beauty.R;
import com.lu.beauty.base.CommonViewHolder;
import com.lu.beauty.bean.DesignerRecommendBean;
import com.lu.beauty.my.LoginActivity;

import java.util.ArrayList;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;


/**
 * Created by GuoXuanYu on 16/11/24.
 */

public class DesignerAllAdapter extends RecyclerView.Adapter<CommonViewHolder> {

    private ArrayList<DesignerRecommendBean.DataBean.DesignersBean> arrayList;

    private Context context;

    private DesignerClickListener designerClickListener;

    private String mCollectionsData;


    public DesignerAllAdapter(DesignerClickListener designerClickListener, Context context) {
        this.designerClickListener = designerClickListener;
        this.context = context;

    }

    public void setArrayList(ArrayList<DesignerRecommendBean.DataBean.DesignersBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return CommonViewHolder.getViewHolder(parent, R.layout.fragment_designer_item);

    }

    public void onBindViewHolder(final CommonViewHolder holder, final int position) {
        holder.setText(R.id.design_item_name, arrayList.get(position).getName());
        holder.setText(R.id.design_item_label, arrayList.get(position).getLabel());
        holder.setCircleImage(R.id.design_item_avatar, arrayList.get(position).getAvatar_url());
        holder.setImage(R.id.design_item_images, arrayList.get(position).getRecommend_images().get(0));

        if (arrayList.get(position).getType() == 1) {
            holder.setTextVisibale(R.id.design_item_follow);
            holder.setText(R.id.design_item_follow, arrayList.get(position).getFollow_num() + " 关注");
        }

        holder.setItemClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                designerClickListener.allAdapterItemClick(arrayList.get(position).getId());

            }
        });

        // TODO  周云霄



    }

    @Override
    public int getItemCount() {

        return arrayList == null ? 0 : arrayList.size();


    }
}
