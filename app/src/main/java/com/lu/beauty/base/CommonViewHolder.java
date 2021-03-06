package com.lu.beauty.base;

import android.content.Context;
import android.graphics.Bitmap;


import android.graphics.Color;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.lu.beauty.R;
import com.lu.beauty.tools.CircleDrawable;

/**
 * Created by dllo on 16/10/31.
 * 通用的ViewHolder
 */
public class CommonViewHolder extends RecyclerView.ViewHolder{
    // SparseArray 用法和HashMap一样
    // 但是key 固定是int类型
    // 用它来存放所有的View, key就是View的id
    private SparseArray<View> views;
    private View itemView; // 行布局
    private RecyclerView recyclerView;
    private Context mContext;

    public CommonViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        this.mContext = itemView.getContext();
        views = new SparseArray<>();
    }

    /**
     * 通过View的id来获得指定View
     * 如果该View没有赋值, 就先执行findViewById
     * 然后把它放到View的集合里
     * 使用泛型来取消强转
     * @param id
     * @return  指定View
     */
    public <T extends View> T getView(int id) {
        View view = views.get(id);
        if (view == null) {
            // 证明SparseArray里没有这个View
            view = itemView.findViewById(id);
            views.put(id, view);
        }
        return (T) view;
    }
    // 返回行布局
    public View getItemView() {
        return itemView;
    }

    // 专门给ListView使用的方法
    public static CommonViewHolder getViewHolder(View itemView, ViewGroup viewGroup, int itemId){
        CommonViewHolder viewHolder;
        if (itemView == null) {
            Context context = viewGroup.getContext();
            itemView = LayoutInflater.from(context).inflate(itemId, viewGroup, false);
            viewHolder = new CommonViewHolder(itemView);
            itemView.setTag(viewHolder);
        }else {
            viewHolder = (CommonViewHolder) itemView.getTag();
        }
        return viewHolder;

    }

    // 专门给RecycleView使用的方法
    public static CommonViewHolder getViewHolder(ViewGroup parent, int itemId) {
        return getViewHolder(null, parent, itemId);
    }
    // 给头布局使用的方法
    public static CommonViewHolder getHeadViewHolder(View view){
        return new CommonViewHolder(view);
    }

    /*********ViewHolder 设置数据的方法***********/
    // 设置文字
    public CommonViewHolder setText(int id, String text) {
        TextView textView = getView(id);
        textView.setText(text);
        return this;
    }
    //给Button设置背景 zyx
    public CommonViewHolder setBackGround(int id, int  bitmap){
        Button btn = getView(id);
        btn.setBackgroundResource(bitmap);
        return this;
    }
    //背景颜色和字迹颜色
    public CommonViewHolder setBackColor(int id, int  color,int colour){
        Button btn = getView(id);
        btn.setBackgroundColor(color);
        btn.setTextColor(colour);
        return this;
    }

    // 设置PopupWindow中选中状态时Text的背景
    public CommonViewHolder setTextBackground(int id, int type){
        TextView textView = getView(id);
        textView.setBackground(mContext.getDrawable(type));
        return this;
    }

    public CommonViewHolder setTextColor(int id,int type){
        TextView textView = getView(id);
        textView.setTextColor(type);
        return this;
    }

    public CommonViewHolder setButtonText(int id,String text){
        Button btn = getView(id);
        btn.setText(text);
        return this;
    }
    //


    public CommonViewHolder setImage(int id, int imgId) {
        ImageView imageView = getView(id);
        imageView.setImageResource(imgId);
        return this;
    }

    // 获取圆形图片
    public CommonViewHolder setCircleImage(int id, String url){
        final ImageView imageView = getView(id);
        Glide.with(mContext).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                CircleDrawable drawable = new CircleDrawable(resource);
                imageView.setImageDrawable(drawable);
            }
        });
        // 网络请求图片
        return this;
    }


    public CommonViewHolder setImage(int id, String url){
        ImageView imageView = getView(id);
        // 网络请求图片
        Glide.with(mContext).load(url).into(imageView);
        Glide.with(mContext)
                .load(url)
                .centerCrop()
                .placeholder(R.mipmap.loading)
                .error(R.mipmap.error)
                .crossFade()
                .into(imageView);
        return this;
    }

    public CommonViewHolder setItemClick(View.OnClickListener listener) {
        itemView.setOnClickListener(listener);
        return this;
    }

    public CommonViewHolder setViewClick(int id, View.OnClickListener listener) {

        getView(id).setOnClickListener(listener);

        return this;
    }




    public CommonViewHolder setTextVisibale(int id){
        TextView textView = getView(id);
        textView.setVisibility(View.VISIBLE); // 设置为可见
        return this;
    }
    public CommonViewHolder setButtonInvisibale(int id){
        Button button = getView(id);
        button.setVisibility(View.INVISIBLE); // 设置为不可见
        return this;
    }

//    public void setBanner(int sale_rv_banner, int center, int i, int circleIndicator, List<String> imgUrls) {
//
//        Banner banner = getView(sale_rv_banner);
//        banner.setBannerStyle(circleIndicator);
//        banner.setImageLoader(new GlideImageLoder());
//        banner.setImages(imgUrls);
//        banner.setBannerAnimation(Transformer.Default);
//        banner.isAutoPlay(true);
//        banner.setDelayTime(i);
//        banner.setIndicatorGravity(center);
//        banner.start();
//    }





}
