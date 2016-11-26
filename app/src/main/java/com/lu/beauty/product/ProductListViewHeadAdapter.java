package com.lu.beauty.product;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.lu.beauty.R;
import com.lu.beauty.base.CommonViewHolder;
import com.lu.beauty.bean.ProductDailyBean;
import com.lu.beauty.tools.AcquisitionTime;

import java.util.ArrayList;
import java.util.Date;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * If the operation is no problem, it is written by wangqiaosheng
 * , otherwise it is written by zhouyunxiao
 */

public class ProductListViewHeadAdapter extends BaseAdapter implements StickyListHeadersAdapter{
    private ProductDailyBean bean;
    private ArrayList<String> arrayList;

    public ProductListViewHeadAdapter() {
        int day = AcquisitionTime.getDay();
        arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (0 == i){
                arrayList.add("TODAY");
            } else if (1 == i){
                arrayList.add("YESTERDAY");
            }else {

            arrayList.add(AcquisitionTime.getMouth() + day);
            }
            day = day - 1;
        }

    }

    public void setBean(ProductDailyBean bean) {
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean.getData().getProducts().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getData().getProducts().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(convertView, parent, R.layout.item_product_daily);

        ListView listView = (ListView) viewHolder.getItemView().findViewById(R.id.lv_product_body);
        ProductListViewBodyAdapter adapter = new ProductListViewBodyAdapter();
        adapter.setBean(bean);
        listView.setAdapter(adapter);


        return viewHolder.getItemView();
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(convertView, parent, R.layout.item_product_head);
        viewHolder.setText(R.id.product_head_tv, arrayList.get(position));
        return viewHolder.getItemView();

    }

    @Override
    public long getHeaderId(int position) {
        return position;
    }
}
