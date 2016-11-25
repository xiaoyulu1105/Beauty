package com.lu.beauty.product;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lu.beauty.R;
import com.lu.beauty.base.CommonViewHolder;
import com.lu.beauty.bean.ProductDailyBean;
import com.lu.beauty.ui.CryFaceView;
import com.lu.beauty.ui.SmileFaceView;

/**
 * If the operation is no problem, it is written by wangqiaosheng
 * , otherwise it is written by zhouyunxiao
 */

public class ProductListViewAdapter extends BaseAdapter{
    ProductDailyBean bean;

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
        viewHolder.setText(R.id.daily_item_title, bean.getData().getProducts().get(position).getName())
                .setText(R.id.daily_item_name, bean.getData().getProducts().get(position).getDesigner().getName())
                .setText(R.id.daily_item_label, bean.getData().getProducts().get(position).getDesigner().getLabel())
                .setImage(R.id.daily_item_img, bean.getData().getProducts()
                        .get(position).getCover_images().get(0), parent.getContext())
                .setCircleImage(R.id.daily_item_user_icon, bean.getData()
                        .getProducts().get(position).getDesigner().getAvatar_url(), parent.getContext());

        int likeCount, disLikeCount;
        likeCount = bean.getData().getProducts().get(position).getLike_user_num();
        disLikeCount = bean.getData().getProducts().get(position).getUnlike_user_num();

        // 喜欢和不喜欢的高度
        double likeHeight = GetPercent.getLikeHigh(likeCount, disLikeCount);
        double dislikeHeight = GetPercent.getDislikeHigh(likeCount, disLikeCount);
        // 喜欢和不喜欢的百分比
        double likePercent = GetPercent.getLikePercent(likeCount, disLikeCount);
        double dislikePercent = GetPercent.getDislikePercent(likeCount, disLikeCount);

        CryFaceView cryFaceView = viewHolder.getView(R.id.daily_item_cry);
        cryFaceView.setDP2PX_final((int) dislikeHeight);

        SmileFaceView smileFaceView = viewHolder.getView(R.id.daily_item_laugh);
        smileFaceView.setDP2PX_FINAL((int) likeHeight);

        return viewHolder.getItemView();
    }

}
