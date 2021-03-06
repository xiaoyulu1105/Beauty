package com.lu.beauty.my;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lu.beauty.R;
import com.lu.beauty.base.CommonViewHolder;

import com.lu.beauty.designer.threadactivity.DesignerItemActivity;

import java.util.ArrayList;

/**
 * Created by  AngleXiao on 16/12/3.
 * OLiGei  what is your name
 * 轻松拿下一个类 属实有牌面
 */
public class FavoriteAdapter extends BaseAdapter {

    private ArrayList<Attention> mAttentions;

    public void setAttentions(ArrayList<Attention> attentions) {
        mAttentions = attentions;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mAttentions == null ? 0 : mAttentions.size();
    }

    @Override
    public Object getItem(int position) {
        return mAttentions.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        final Context context = parent.getContext();
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(convertView, parent, R.layout.fragment_designer_item);


        viewHolder.setText(R.id.design_item_name,mAttentions.get(position).getAttentionName());
        viewHolder.setText(R.id.design_item_label, mAttentions.get(position).getAttentionLabel());
        viewHolder.setCircleImage(R.id.design_item_avatar, mAttentions.get(position).getAttentionAvatar());
        viewHolder.setImage(R.id.design_item_images, mAttentions.get(position).getAttentionImage());
        viewHolder.setButtonInvisibale(R.id.design_item_button);
        viewHolder.setItemClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = mAttentions.get(position).getAttentionId();
                Intent intent = new Intent(context, DesignerItemActivity.class);
                intent.putExtra(DesignerItemActivity.INTENT_ID_KEY,id);
                context.startActivity(intent);


            }
        });
        return viewHolder.getItemView();

    }


}
