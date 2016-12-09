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
    private boolean isClick; // true代表关注, 默认未关注
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

        // type 起什么作用 用来区分是否为 最受欢迎 的设计师
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

        // 将数据保存
        String attentionName = arrayList.get(position).getName();
        String attentionLabel = arrayList.get(position).getLabel();
        String attentionAvatar = arrayList.get(position).getAvatar_url();
        String attentionImage = arrayList.get(position).getRecommend_images().get(0);
        String attentionId = String.valueOf(arrayList.get(position).getId());

        ArrayList<AttentionUser> attention = new ArrayList<>();
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

        //判断登录
        AttentionUser designerAttentionUser = AttentionUser.getCurrentUser(AttentionUser.class);

        // !!!
        //在点击事件之前判断是否登录 如果登录就判断是否关注过
        if (designerAttentionUser != null) {

            if (attentionCount == 0) {
                Log.d("DesignerAllAdapter", "还没有关注的设计师");

            } else {

                // 遍历 bmob 上关注的设计师, 将关注项的文字变为已关注
                mArrayList1 = new ArrayList<>();
                mArrayList1 = designerAttentionUser.getAttentionList();

                for (int i = 0; i < mArrayList1.size(); i++) {
                    if (mCollectionsData.equals(mArrayList1.get(i))) {
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

        } else {
            // 非登录状态
            Log.d("DesignerAllAdapter", "未登录, 所以都是未关注");
        }

        // 点击事件
        holder.setViewClick(R.id.design_item_button, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AttentionUser designerAttentionUser = AttentionUser.getCurrentUser(AttentionUser.class);

                if (designerAttentionUser != null) {
                    // 当点击时 处于登录状态

                    if (!isClick) {
                        // 从未关注 状态 变为 已关注状态
                        holder.setButtonText(R.id.design_item_button, "已关注");
                        holder.setBackColor(R.id.design_item_button, Color.BLACK, Color.WHITE);


                        ArrayList<String>  arrayList = new ArrayList<>();

                        // TODO 保存该条数据到bmob
                        if (attentionCount == 0) {
                            // 第一次关注, 不用获取集合数据
                            attentionCount++;

                        } else {
                            arrayList = designerAttentionUser.getAttentionList();
                        }

                        arrayList.add(mCollectionsData);
                        designerAttentionUser.setAttentionList(arrayList);
                        designerAttentionUser.update(new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    Toast.makeText(context, "关注啦啦啦☺", Toast.LENGTH_SHORT).show();
                                    isClick = !isClick;

                                } else {
                                    Log.d("DesignerAllAdapter", "点击关注 不成功");
                                }
                            }
                        });


                    } else if (isClick) {
                        // 从关注 状态 变为 未关注状态
                        holder.setButtonText(R.id.design_item_button, "+ 关注");
                        holder.setBackColor(R.id.design_item_button, Color.parseColor("#74D5DA"), Color.BLACK);


                        // TODO 将Bmob中的该条数据删除
                        if (designerAttentionUser != null) {

                            ArrayList<String> arrayList = new ArrayList<>();
                            arrayList = designerAttentionUser.getAttentionList();
                            Log.d("DesignerAllAdapter", "mArrayList2.size():" + arrayList.size());

                            for (int i = arrayList.size() - 1; i >= 0; i--) {
                                if (mCollectionsData.equals(arrayList.get(i))) {
                                    arrayList.remove(i);

                                    designerAttentionUser.setAttentionList(arrayList);
                                    Log.d("DesignerAllAdapter", "for循环里 取消关注成功");
                                    Log.d("DesignerAllAdapter", "arrayList.size():" + arrayList.size());
                                }

                            }

                            designerAttentionUser.update(new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e == null) {
                                        Log.d("DesignerAllAdapter", "update方法里 取消关注成功");
                                        Toast.makeText(context, "取消关注", Toast.LENGTH_SHORT).show();

                                        attentionCount--;
                                        isClick = !isClick;
                                    }
                                }
                            });

                        }

                    }


                } else {
                    // 当点击时 处于非登录状态 让跳转

                    Log.d("DesignerAllAdapter", "还未登录, 请先登录!");

                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);

//                    holder.setButtonText(R.id.design_item_button, "+ 关注");
//                    holder.setBackColor(R.id.design_item_button, Color.parseColor("#74D5DA"), Color.BLACK);
//                    isClick = !isClick;
                }

            }
        });

    }


    @Override
    public int getItemCount() {

        return arrayList == null ? 0 : arrayList.size();


    }
}
