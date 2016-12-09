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

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;


/**
 * Created by GuoXuanYu on 16/11/24.
 */

public class DesignerAllAdapter extends RecyclerView.Adapter<CommonViewHolder> {
    private ArrayList<DesignerRecommendBean.DataBean.DesignersBean> arrayList;

    private Context context;
    private boolean isClick = false; // true代表关注, 默认未关注
    // by 小玉
    private static int attentionCount = 0;

    private DesignerClickListener designerClickListener;
    private ArrayList<String> mArrayList1;
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

        String attentionName = arrayList.get(position).getName();
        String attentionLabel = arrayList.get(position).getLabel();
        String attentionAvatar = arrayList.get(position).getAvatar_url();
        String attentionImage = arrayList.get(position).getRecommend_images().get(0);
        String attentionId = String.valueOf(arrayList.get(position).getId());

        ArrayList<AttentionUser> attention = new ArrayList<AttentionUser>();
        AttentionUser attentionUser = new AttentionUser();
        attentionUser.setAttentionId(attentionId);
        attentionUser.setAttentionName(attentionName);
        attentionUser.setAttentionLabel(attentionLabel);
        attentionUser.setAttentionAvatar(attentionAvatar);
        attentionUser.setAttentionImage(attentionImage);

        attention.add(attentionUser);
        Collections collections = new Collections();//关注的所有的人
        collections.setAttentionUsers(attention);

        Gson gson = new Gson();
        mCollectionsData = gson.toJson(collections);
        mArrayList1 = new ArrayList<String>();


        //判断登录


        AttentionUser designerAttentionUser = AttentionUser.getCurrentUser(AttentionUser.class);


        // !!!
        //在点击事件之前判断是否登录 如果登录就判断是否关注过
        if (designerAttentionUser != null) {

            mArrayList1 = designerAttentionUser.getAttentionList();


            for (int i = 0; i < mArrayList1.size(); i++) {
                if (mCollectionsData.equals(mArrayList1.get(i))){
                    isClick = true; // 关注
                    holder.setButtonText(R.id.design_item_button, "已关注");
                    holder.setBackColor(R.id.design_item_button, Color.BLACK, Color.WHITE);

                   break;
                } else {
                    isClick = false;
                    holder.setButtonText(R.id.design_item_button, "+ 关注");
                    holder.setBackColor(R.id.design_item_button, Color.parseColor("#74D5DA"), Color.BLACK);
                }
            }
        }

        //非登录状态 让跳转
        else {
            Log.d("DesignerAllAdapter", "未登录");
        }

       holder.setViewClick(R.id.design_item_button, new View.OnClickListener() {
            @Override
           public void onClick(View v) {

                AttentionUser designerAttentionUser = AttentionUser.getCurrentUser(AttentionUser.class);

                // 将 数据存入 Bmob 的attentionList集合

                if (!isClick ) {
                    // 从未关注 状态 变为 已关注状态
                    holder.setButtonText(R.id.design_item_button, "已关注");
                    holder.setBackColor(R.id.design_item_button, Color.BLACK, Color.WHITE);
                    isClick = !isClick;


                    // TODO 保存该条数据到bmob
                    if (designerAttentionUser != null) {
//                        for (int i = 0; i < mArrayList1.size(); i++) {
//                            if (mCollectionsData.equals(mArrayList1.get(i))) {
//                            }
//                        }
                        if (attentionCount == 0) {
                            // 第一次关注, 不用获取集合数据
                            attentionCount++;

                        } else {
                            mArrayList1 = designerAttentionUser.getAttentionList();
                        }
////插入,mob
                        mArrayList1.add(mCollectionsData);
                        designerAttentionUser.setAttentionList(mArrayList1);
                        designerAttentionUser.update(new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    Toast.makeText(context, "关注啦啦啦☺", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "不成功", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                    //非登录状态 让跳转
                    else {
                        Log.d("DesignerAllAdapter", "未登录");

                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);

                        Toast.makeText(context, "非登录状态", Toast.LENGTH_SHORT).show();
                        holder.setButtonText(R.id.design_item_button, "+ 关注");
                        holder.setBackColor(R.id.design_item_button, Color.parseColor("#74D5DA"), Color.BLACK);
                        isClick =! isClick;
                    }


                } else {
                    holder.setButtonText(R.id.design_item_button, "+ 关注");
                    holder.setBackColor(R.id.design_item_button, Color.parseColor("#74D5DA"), Color.BLACK);
                    isClick =! isClick;

                    // TODO 将Bmob中的该条数据删除
                    // 从关注 状态 变为 未关注状态
                    if (designerAttentionUser != null) {

                ArrayList<String> mArrayList2 = new ArrayList<String>();
                        mArrayList2 = designerAttentionUser.getAttentionList();
                        Log.d("DesignerAllAdapter", "mArrayList2.size():" + mArrayList2.size());
                        for (int i =  mArrayList2.size()-1; i>=0 ; i--) {
                            if (mCollectionsData.equals(mArrayList2.get(i))){
                                mArrayList2.remove(i);
                                designerAttentionUser.setAttentionList(mArrayList2);
                                Log.d("DesignerAllAdapter", "mArrayList2.size():" + mArrayList2.size());
                            }

                        }
//                        mArrayList1.remove(mCollectionsData);
                        //mArrayList2.remove(mCollectionsData);
                        designerAttentionUser.update(new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                Toast.makeText(context, "取消关注", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }


                }
         }
       });

    }


    //插入










    @Override
    public int getItemCount() {

        return arrayList == null ? 0 : arrayList.size();


    }
}
