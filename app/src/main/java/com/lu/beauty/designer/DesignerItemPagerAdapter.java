package com.lu.beauty.designer;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.lu.beauty.R;
import com.squareup.picasso.Picasso;


/**
 * Created by GuoXuanYu on 16/12/5.
 */

public class DesignerItemPagerAdapter  extends PagerAdapter{
    private String[] urls;

    @Override
    public int getCount() {
        return urls == null?0:urls.length;
    }

    // 获得图片的数量
    public int getImageCount(){
        return urls.length;
    }

    public void setUrls(String[] urls){
        this.urls =urls;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(container.getContext());
        Picasso.with(container.getContext())
                .load(urls[position%urls.length])
                .fit()
                .placeholder(R.mipmap.loading)
                .into(imageView);
        container.addView(imageView, WindowManager.LayoutParams.MATCH_PARENT
                , WindowManager.LayoutParams.WRAP_CONTENT);
        return imageView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (container.getChildAt(position) == object){
            container.removeViewAt(position);
        }
    }
}
