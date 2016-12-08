package com.lu.beauty.my;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lu.beauty.R;
import com.lu.beauty.base.CommonViewHolder;
import com.lu.beauty.designer.AttentionUser;
import com.lu.beauty.designer.Collections;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.bmob.v3.BmobUser;

/**
 * Created by  AngleXiao on 16/12/3.
 * OLiGei  what is your name
 * 轻松拿下一个类 属实有牌面
 */
public class FavoriteAdapter extends BaseAdapter {

 private ArrayList<AttentionUser> mAttentionUsers;

    public void setAttentionUsers(ArrayList<AttentionUser> attentionUsers) {
        mAttentionUsers = attentionUsers;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mAttentionUsers == null ? 0 : mAttentionUsers.size();
    }

    @Override
    public Object getItem(int position) {
        return mAttentionUsers.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(convertView, parent, R.layout.fragment_designer_item);


      viewHolder.setText(R.id.design_item_name,mAttentionUsers.get(position).getAttentionName());
        viewHolder.setText(R.id.design_item_label, mAttentionUsers.get(position).getAttentionLabel());
        viewHolder.setCircleImage(R.id.design_item_avatar, mAttentionUsers.get(position).getAttentionAvatar());
        viewHolder.setImage(R.id.design_item_images, mAttentionUsers.get(position).getAttentionImage());

        return viewHolder.getItemView();

    }


}
