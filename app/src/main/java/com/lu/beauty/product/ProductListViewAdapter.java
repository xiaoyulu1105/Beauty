package com.lu.beauty.product;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.lu.beauty.R;
import com.lu.beauty.base.CommonViewHolder;
import com.lu.beauty.bean.ProductCommonBean;
import com.lu.beauty.product.productitem.ProductItemActivity;
import com.lu.beauty.tools.GetPercent;
import com.lu.beauty.ui.CryFaceView;
import com.lu.beauty.ui.SmileFaceView;

import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * If the operation is no problem, it is written by wangqiaosheng
 * , otherwise it is written by zhouyunxiao
 *
 * 显示有物数据的ListView的适配器, 也是在这里显示笑脸
 */

public class ProductListViewAdapter extends BaseAdapter implements StickyListHeadersAdapter{
    private ArrayList<ProductCommonBean.DataBean.ProductsBean> arrayList;

    public void setArrayList(ArrayList<ProductCommonBean.DataBean.ProductsBean> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
            int count = 0;
        try {
            count = arrayList == null ? 0 : arrayList.size();
        }catch (Exception e){
            count = 0;
        }
        return  count;
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
        final Context context = parent.getContext();

        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(convertView, parent, R.layout.item_product_body);
        Log.d("ProductListViewAdapter", "arrayList:" + arrayList);
        viewHolder.setText(R.id.daily_item_title, arrayList.get(position).getName())
                .setText(R.id.daily_item_name, arrayList.get(position).getDesigner().getName())
                .setText(R.id.daily_item_label, arrayList.get(position).getDesigner().getLabel())
                .setImage(R.id.daily_item_img, arrayList.get(position).getCover_images().get(0))
                .setCircleImage(R.id.daily_item_user_icon, arrayList.get(position).getDesigner().getAvatar_url());

        ArrayList<String> picRUrls = new ArrayList<>();
        picRUrls.addAll(arrayList.get(position).getCover_images());

        // 喜欢和不喜欢的值
        int likeCount, disLikeCount;
        likeCount = arrayList.get(position).getLike_user_num();
        disLikeCount = arrayList.get(position).getUnlike_user_num();

        // 喜欢和不喜欢的高度
        double likeHeight = GetPercent.getLikeHigh(likeCount, disLikeCount);
        double dislikeHeight = GetPercent.getDislikeHigh(likeCount, disLikeCount);
        // 喜欢和不喜欢的百分比
        int likePercent = (int) GetPercent.getLikePercent(likeCount, disLikeCount);
        int dislikePercent = 100 - likePercent; // 100% 的值 为100
        // 找到两个表情, 设置高度
        CryFaceView cryFaceView = viewHolder.getView(R.id.daily_item_cry);
        SmileFaceView smileFaceView = viewHolder.getView(R.id.daily_item_laugh);
        cryFaceView.setDP2PX_final((int) dislikeHeight);
        smileFaceView.setDP2PX_FINAL((int) likeHeight);

        // 将表情对象 进行关联, 不可行的方式, 可行方式在自定义组件里就写了
        cryFaceView.setSmileFaceView(smileFaceView);
        smileFaceView.setCryFaceView(cryFaceView);

        // 在这里监听点击无响应

        // 老师加的??
//        viewHolder.getView(R.id.daily_item_user_icon).setOnClickListener(new MyListener(arrayList.get(position).getDesigner().getId() + "", parent.getContext()));
//        viewHolder.getView(R.id.daily_item_img).setOnClickListener(new ProductListener(arrayList.get(position).getId() + "", parent.getContext(),picRUrls));


        return viewHolder.getItemView();
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(convertView, parent, R.layout.null_layout);
        return viewHolder.getItemView();
    }

    @Override
    public long getHeaderId(int position) {
        return position;
    }

    class MyListener implements View.OnClickListener{
        String id;
        Context context;

        public MyListener(String id, Context context) {
            this.id = id;
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, ProductItemActivity.class);
            intent.putExtra("id", id);
            context.startActivity(intent);
        }
    }

    class ProductListener implements View.OnClickListener{
        String id;
        Context context;
        ArrayList<String> urlList;

        public ProductListener(String id, Context context,ArrayList<String> urlList) {
            this.id = id;
            this.context = context;
            this.urlList = urlList;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, ProductItemActivity.class);
            intent.putExtra("id", id);
//            Log.d("ProductListener11", "picRUrls:" + picRUrls);
            Log.d("ProductListener", "urlList:" + urlList);
            intent.putStringArrayListExtra("pics", urlList);
            context.startActivity(intent);
        }
    }

}
