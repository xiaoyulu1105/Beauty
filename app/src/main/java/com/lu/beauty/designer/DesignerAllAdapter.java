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
import java.util.Collection;
import java.util.Iterator;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;


/**
 * Created by GuoXuanYu on 16/11/24.
 */

public class DesignerAllAdapter extends RecyclerView.Adapter<CommonViewHolder> {
    private ArrayList<DesignerRecommendBean.DataBean.DesignersBean> arrayList;

    private Context context;
    private boolean isClick;
    // by 小玉
    private static int attentionCount = 0;

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



    public void onBindViewHolder(final CommonViewHolder holder, final int position) {
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


//        //        // TODO  周云霄
        holder.setViewClick(R.id.design_item_button, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                String collectionsData = gson.toJson(collections);


                if (!isClick) {

                    holder.setButtonText(R.id.design_item_button, "已关注");
                    holder.setBackColor(R.id.design_item_button, Color.BLACK, Color.WHITE);
                    isClick = !isClick;


                    //判断是否登录
                    AttentionUser designerAttentionUser = AttentionUser.getCurrentUser(AttentionUser.class);
//                     BmobUser bmobUser = BmobUser.getCurrentUser();
//                    if (designerAttentionUser != null) {
//
//                        //判断是否关注
//                        //关注
//                        if (collectionsData.equals(designerAttentionUser.getAttentionName())){
//
//                        }else {
//                            designerAttentionUser.setAttentionName(collectionsData);
//
//                            designerAttentionUser.update(new UpdateListener() {
//                                @Override
//                                public void done(BmobException e) {
//                                    if (e == null) {
//                                        Toast.makeText(context, "上传成功", Toast.LENGTH_SHORT).show();
//                                    } else {
//                                        Toast.makeText(context, "上传失败", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
//                        }
//
//                    }


                    // !!!
                    // 将 数据存入 Bmob 的attentionList集合
                    if (designerAttentionUser != null) {

                        Log.d("DesignerAllAdapter", "已登录");

                        ArrayList<String> arrayList = new ArrayList<String>();

                        if (attentionCount == 0) {
                            // 第一次关注, 不用获取集合数据
                            attentionCount++;

                        } else {
                            arrayList = designerAttentionUser.getAttentionList();
                        }

                        //判断是否关注
                        //关注
                        arrayList.add(collectionsData);
                        designerAttentionUser.setAttentionList(arrayList);

                        designerAttentionUser.update(new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                   // Toast.makeText(context, "上传成功", Toast.LENGTH_SHORT).show();
                                } else {
                                  //  Toast.makeText(context, "上传失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    // !!!

                    //非登录状态 让跳转
                    else {
                        Log.d("DesignerAllAdapter", "未登录");

                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);

                        Toast.makeText(context, "非登录状态", Toast.LENGTH_SHORT).show();
                        holder.setButtonText(R.id.design_item_button, "+ 关注");
                        isClick = !isClick;
                        holder.setBackColor(R.id.design_item_button, Color.parseColor("#74D5DA"), Color.BLACK);
                    }


                } else {
                    holder.setButtonText(R.id.design_item_button, "+ 关注");
                    holder.setBackColor(R.id.design_item_button, Color.parseColor("#74D5DA"), Color.BLACK);
                    isClick = !isClick;

                }
            }
       });


    }


                @Override
                public int getItemCount () {

                    return arrayList == null ? 0 : arrayList.size();


                }
            }
