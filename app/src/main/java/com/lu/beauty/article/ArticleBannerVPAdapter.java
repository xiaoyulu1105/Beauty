package com.lu.beauty.article;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lu.beauty.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by XiaoyuLu on 16/12/6.
 * 该适配器 用于显示 画报第二级中 点击图片后出现 的轮播图
 */

public class ArticleBannerVPAdapter extends PagerAdapter {

    private ArrayList<String> mImageUrlList; // 图片网址的集合

    // 图片集合的 set get 方法
    public ArrayList<String> getImageUrlList() {
        return mImageUrlList;
    }
    public void setImageUrlList(ArrayList<String> imageUrlList) {
        mImageUrlList = imageUrlList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mImageUrlList == null ? 0 : mImageUrlList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    // 手动复写的 instantiateItem 方法
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Context context = container.getContext();
        ImageView imageView = new ImageView(context);

        Glide.with(context)
                .load(mImageUrlList.get(position))
                .placeholder(R.mipmap.loading) // 加载时的图片
                .into(imageView);

        container.addView(imageView,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        return imageView;
    }

    // 手动 复写 destroyItem 方法
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (container.getChildAt(position) == object) {
            container.removeViewAt(position);
        }
    }
}
