package com.lu.beauty.richtext;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.lu.beauty.article.ArticleImageSingleton;

import java.util.ArrayList;

/**
 * Created by XiaoyuLu on 16/12/1.
 * <p>
 * 在这里 可以实现图片网址的获取
 */
public class UrlImageGetter implements Html.ImageGetter {
    private Context mContext;
    private TextView mTextView;
    private int width;

    private ArticleImageSingleton singleton = ArticleImageSingleton.getInstance();
    private ArrayList<String> mArrayList = new ArrayList<>();


    public UrlImageGetter(Context context, TextView textView) {
        mContext = context;
        mTextView = textView;
        width = mContext.getResources().getDisplayMetrics().widthPixels;
    }


    @Override
    public Drawable getDrawable(String source) {
        final UrlDrawable urlDrawable = new UrlDrawable();
        //
        final String source1 = source;
        Glide.with(mContext).load(source).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                if (resource != null) {
                    urlDrawable.mBitmap = resource;
                    urlDrawable.setBounds(0, 0, resource.getWidth(), resource.getHeight());
                    mTextView.invalidate();
                    mTextView.setText(mTextView.getText());

                    // TODO 当图片网址不为空时, 获取图片网址.
                    Log.d("UrlImageGetter", source1);
                    mArrayList.add(source1);
                    Log.d("UrlImageGetter", "size():" + mArrayList.size());
                    singleton.setImageUrlArrayList(mArrayList);
                    Log.d("UrlImageGetter", "singleton.size():" + singleton.getImageUrlArrayList().size());

                }
            }
        });

        singleton.setImageUrlArrayList(mArrayList);
        Log.d("UrlImageGetter", "singleton.size():" + singleton.getImageUrlArrayList().size()); // 0

        return urlDrawable;
    }
}
