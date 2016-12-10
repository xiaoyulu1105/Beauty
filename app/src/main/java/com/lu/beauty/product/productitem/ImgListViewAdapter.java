package com.lu.beauty.product.productitem;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lu.beauty.R;
import com.lu.beauty.base.CommonViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * If the operation is no problem, it is written by wangqiaosheng
 * , otherwise it is written by zhouyunxiao
 */

public class ImgListViewAdapter extends BaseAdapter{
    List<String> arrayList;

    public void setArrayList(List<String> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("ImgListViewAdapter", "arrayList:" + arrayList);
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(convertView, parent, R.layout.item_product_item);
        viewHolder.setImage(R.id.item_product_item_img, arrayList.get(position));

        return viewHolder.getItemView();
    }
}
