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
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
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
        AttentionUser designerAttentionUser = AttentionUser.getCurrentUser(AttentionUser.class);

        // 先显示, 在点击事件之前判断是否登录 如果登录 就判断是否关注过

        if (designerAttentionUser != null) {
            // 当 处于 登录状态时
            Log.d("DesignerAllAdapter", "现在是登录状态");

            String attentionId = String.valueOf(arrayList.get(position).getId());

            // 查询数据库, 当有该用户时
            BmobQuery<Attention> query = new BmobQuery<>();
            query.addWhereEqualTo("myUser", designerAttentionUser); // 查询当前用户的所有关注的 设计师
            query.addWhereEqualTo("attentionId", attentionId);
            query.findObjects(new FindListener<Attention>() {
                @Override
                public void done(List<Attention> list, BmobException e) {
                    if (e == null) {
                        Log.d("DesignerAllAdapter", "查询数据库成功");
                        if (list.size() > 0) {
                            // 这个设计师 处于关注状态
                            holder.setButtonText(R.id.design_item_button, "已关注");
                            holder.setBackColor(R.id.design_item_button, Color.BLACK, Color.WHITE);

                        } else {
                            holder.setButtonText(R.id.design_item_button, "+ 关注");
                            holder.setBackColor(R.id.design_item_button, Color.parseColor("#74D5DA"), Color.BLACK);
                        }

                    } else {
                        Log.d("DesignerAllAdapter", "查询数据库失败");
                    }
                }
            });

        }


        // 点击事件
        holder.setViewClick(R.id.design_item_button, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 点击时打印 是否 处于关注状态

                final AttentionUser designerAttentionUser = AttentionUser.getCurrentUser(AttentionUser.class);
                Log.d("DesignerAllAdapter", "用户的信息:" + designerAttentionUser);


                if (designerAttentionUser != null) {
                    // 当点击时 处于登录状态
                    Log.d("DesignerAllAdapter", "处于登录状态");


                    String attentionId1 = String.valueOf(arrayList.get(position).getId());
                    // 查询数据库, 当有该用户时
                    BmobQuery<Attention> query = new BmobQuery<>();
                    query.addWhereEqualTo("myUser", designerAttentionUser); // 查询当前用户的所有关注的设计师
                    query.addWhereEqualTo("attentionId", attentionId1);
                    query.findObjects(new FindListener<Attention>() {
                        @Override
                        public void done(List<Attention> list, BmobException e) {
                            if (e == null) {
                                Log.d("DesignerAllAdapter", "查询数据库成功");
                                if (list.size() > 0) {
                                    // 这个设计师 处于关注状态
                                    String objectId = list.get(0).getObjectId();
                                    Log.d("DesignerAllAdapter111", objectId);
                                    // 调用删除方法
                                    removeDataFormBmob(objectId);
                                    Log.d("DesignerAllAdapter111", "走了这个方法");
                                    holder.setButtonText(R.id.design_item_button, "+ 关注");
                                    holder.setBackColor(R.id.design_item_button, Color.parseColor("#74D5DA"), Color.BLACK);

                                } else {
                                    // 从 未关注 到 关注
                                    Attention attentionSingleBean = new Attention();
                                    // 调用方法 添加 收藏类 Attention的数据
                                    attentionSingleBean = getAttentionData(arrayList, position, designerAttentionUser);
                                    attentionSingleBean.save(new SaveListener<String>() {
                                        @Override
                                        public void done(String s, BmobException e) {
                                            if (e == null) {
                                                holder.setButtonText(R.id.design_item_button, "已关注");
                                                holder.setBackColor(R.id.design_item_button, Color.BLACK, Color.WHITE);

                                                Log.d("DesignerAllAdapter", "保存数据库成功");
                                                Toast.makeText(context, "关注成功", Toast.LENGTH_SHORT).show();

                                            } else {
                                                Log.d("DesignerAllAdapter", "保存数据库失败");
                                                Toast.makeText(context, "关注失败", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }

                            } else {
                                Log.d("DesignerAllAdapter", "查询数据库失败");
                            }
                        }
                    });

                } else {
                    // 当点击时 处于非登录状态 让跳转
                    loginFirst();
                }

            }
        });

    }


    /**
     * 将数据存储在
     * @param arrayList
     * @param position
     * @param designerAttentionUser
     */
    private Attention getAttentionData(ArrayList<DesignerRecommendBean.DataBean.DesignersBean> arrayList, int position, AttentionUser designerAttentionUser) {
        String attentionName = arrayList.get(position).getName();
        String attentionLabel = arrayList.get(position).getLabel();
        String attentionAvatar = arrayList.get(position).getAvatar_url();
        String attentionImage = arrayList.get(position).getRecommend_images().get(0);
        String attentionId = String.valueOf(arrayList.get(position).getId());
        // 将数据 save 存入 Attention 类
        Attention attentionSingleBean = new Attention();
        attentionSingleBean.setAttentionId(attentionId);
        attentionSingleBean.setAttentionName(attentionName);
        attentionSingleBean.setAttentionLabel(attentionLabel);
        attentionSingleBean.setAttentionAvatar(attentionAvatar);
        attentionSingleBean.setAttentionImage(attentionImage);

        attentionSingleBean.setMyUser(designerAttentionUser);

        return attentionSingleBean;
    }

    /**
     * 先登录
     */
    private void loginFirst() {
        Log.d("DesignerAllAdapter", "还未登录, 请先登录!");
        Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    /**
     * 删除该数据
     *
     * @param objectId
     */
    private void removeDataFormBmob(String objectId) {
        Log.d("DesignerAllAdapter111", "shanchufangfa");
        Attention attention = new Attention();
        attention.setObjectId(objectId);
        attention.delete(objectId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.d("DesignerAllAdapter", "删除数据成功");
                    Toast.makeText(context, "取消成功", Toast.LENGTH_SHORT).show();
                }else {
                    Log.d("DesignerAllAdapter111", e.getMessage());
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        return arrayList == null ? 0 : arrayList.size();


    }
}
