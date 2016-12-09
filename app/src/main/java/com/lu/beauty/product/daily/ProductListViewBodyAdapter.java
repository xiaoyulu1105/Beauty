package com.lu.beauty.product.daily;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lu.beauty.R;
import com.lu.beauty.base.CommonViewHolder;
import com.lu.beauty.bean.ProductDailyBean;
import com.lu.beauty.designer.threadactivity.DesignerItemActivity;
import com.lu.beauty.product.ProductItemActivity;
import com.lu.beauty.tools.GetPercent;
import com.lu.beauty.ui.CryFaceView;
import com.lu.beauty.ui.SmileFaceView;

import java.util.ArrayList;


/**
 * If the operation is no problem, it is written by wangqiaosheng
 * , otherwise it is written by zhouyunxiao
 */

public class ProductListViewBodyAdapter extends BaseAdapter{
    private ProductDailyBean bean;

    public void setBean(ProductDailyBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        int count = 0;
        try{
            count = bean.getData().getProducts().size();
        }catch(Exception e){
            count = 0;
        }
        return count;
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

        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(convertView, parent, R.layout.item_product_body);

        viewHolder.setText(R.id.daily_item_title, bean.getData().getProducts().get(position).getName())
                .setText(R.id.daily_item_name, bean.getData().getProducts().get(position).getDesigner().getName())
                .setText(R.id.daily_item_label, bean.getData().getProducts().get(position).getDesigner().getLabel())
                .setImage(R.id.daily_item_img, bean.getData().getProducts()
                        .get(position).getCover_images().get(0))
                .setCircleImage(R.id.daily_item_user_icon, bean.getData()
                        .getProducts().get(position).getDesigner().getAvatar_url());

       ArrayList<String> picRUrls = new ArrayList<>();
        picRUrls.addAll(bean.getData().getProducts().get(position).getCover_images());
//                bean.getData().getProducts().get(position).getCover_images();
//        for (int i = 0; i < bean.getData().getProducts().get(position).getCover_images().size(); i++) {
//            picRUrls.add(bean.getData().getProducts().get(position).getCover_images().get(i));
//        }

        // 喜欢和不喜欢的值
        int likeCount, disLikeCount;
        likeCount = bean.getData().getProducts().get(position).getLike_user_num();
        disLikeCount = bean.getData().getProducts().get(position).getUnlike_user_num();

        // 喜欢和不喜欢的高度
        double likeHeight = GetPercent.getLikeHigh(likeCount, disLikeCount);
        double dislikeHeight = GetPercent.getDislikeHigh(likeCount, disLikeCount);
        // 喜欢和不喜欢的百分比
        int likePercent = (int) GetPercent.getLikePercent(likeCount, disLikeCount);
        int dislikePercent = 100 - likePercent; // 100% 的值 为100
        // 找到两个表情, 设置高度
        final CryFaceView cryFaceView = viewHolder.getView(R.id.daily_item_cry);
        SmileFaceView smileFaceView = viewHolder.getView(R.id.daily_item_laugh);
        cryFaceView.setDP2PX_final((int) dislikeHeight);
        smileFaceView.setDP2PX_FINAL((int) likeHeight);

        // 找到两个textView, 显示喜欢和不喜欢的百分比
//        final TextView dislikePercentTV = viewHolder.getView(R.id.daily_item_dislike_percent_tv);
//        final TextView likePercentTV = viewHolder.getView(R.id.daily_item_like_percent_tv);

//        dislikePercentTV.setText(dislikePercent + "%" + "无感");
//        likePercentTV.setText(likePercent + "%" + "喜欢");

        // TODO 想通过获取 动画中的布尔值, 判断TextView的显示或不显示, 未完全实现
        // 问题: ViewHolder的重用机制导致未点击的表情一样变色
//        Log.d("ProductListView11", "cryFaceView.isChange():" + cryFaceView.isChange());
//        cryFaceView.addOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (cryFaceView.isChange()) {
//                    dislikePercentTV.setVisibility(View.VISIBLE);
//                    notifyDataSetChanged();
//                } else {
//                    dislikePercentTV.setVisibility(View.INVISIBLE);
//                    notifyDataSetChanged();
//                }
//            }
//        });



        viewHolder.getView(R.id.daily_item_user_icon).setOnClickListener(new MyListener(bean.getData().getProducts().get(position).getDesigner().getId() + "", parent.getContext()));
        viewHolder.getView(R.id.daily_item_img).setOnClickListener(new ProductListener(bean.getData().getProducts().get(position).getId() + "", parent.getContext(),picRUrls));
        return viewHolder.getItemView();



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
            Intent intent = new Intent(context, DesignerItemActivity.class);
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
