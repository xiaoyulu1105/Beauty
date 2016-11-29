package com.lu.beauty.product.daily;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.lu.beauty.R;
import com.lu.beauty.base.CommonViewHolder;
import com.lu.beauty.bean.ProductDailyBean;
import com.lu.beauty.internet.HttpUtil;
import com.lu.beauty.internet.ResponseCallBack;
import com.lu.beauty.product.daily.ProductListViewBodyAdapter;
import com.lu.beauty.tools.AcquisitionTime;

import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * If the operation is no problem, it is written by wangqiaosheng
 * , otherwise it is written by zhouyunxiao
 */

public class ProductListViewHeadAdapter extends BaseAdapter implements StickyListHeadersAdapter{
    private ArrayList<String> arrayList;
    private ArrayList<String> dateArray;

    public ProductListViewHeadAdapter() {
        int day = AcquisitionTime.getDay();
        long date = AcquisitionTime.getDate();
        arrayList = new ArrayList<>();
        dateArray = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (0 == i){
                arrayList.add("TODAY");
            } else if (1 == i){
                arrayList.add("YESTERDAY");
            }else {

                arrayList.add(AcquisitionTime.getMouth() + day);
            }
            dateArray.add(date + "");
            day = day - 1;
            date = date - 24 * 60 * 60 * 1000;
        }
        Log.d("ProductListViewHead1234", "dateArray:" + dateArray);

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

        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(convertView, parent, R.layout.item_product_daily);

       ListView listView = viewHolder.getView(R.id.lv_product_body);
        ProductListViewBodyAdapter adapter = new ProductListViewBodyAdapter();
        Log.d("ProductListViewHeadAdap", dateArray.get(position));
        HttpUtil.getProduckDailyBean(dateArray.get(position), new MyListener(listView,adapter));
//cfy137000

        return viewHolder.getItemView();
    }

    class MyListener implements ResponseCallBack<ProductDailyBean>{
        ListView listView;
        ProductListViewBodyAdapter adapter;

        public MyListener(ListView listView, ProductListViewBodyAdapter adapter) {
            this.listView = listView;
            this.adapter = adapter;
        }

        @Override
        public void onResponse(ProductDailyBean productDailyBean) {
            adapter.setBean(productDailyBean);
            listView.setAdapter(adapter);
        }

        @Override
        public void onError(Exception e) {
            Log.d("ProductListViewHeadAdap", "网络请求失败");
        }
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
