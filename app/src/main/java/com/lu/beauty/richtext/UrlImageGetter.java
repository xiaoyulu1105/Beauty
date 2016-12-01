package com.lu.beauty.richtext;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * Created by XiaoyuLu on 16/12/1.
 */
public class UrlImageGetter implements Html.ImageGetter {
    private Context mContext;
    private TextView mTextView;
    private int width;

    public UrlImageGetter(Context context, TextView textView) {
        mContext = context;
        mTextView = textView;
        width = mContext.getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    public Drawable getDrawable(String source) {
        final UrlDrawable urlDrawable = new UrlDrawable();
        Glide.with(mContext).load(source).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                if (resource != null) {
                    urlDrawable.mBitmap = resource;
                    urlDrawable.setBounds(0, 0, resource.getWidth(), resource.getHeight());
                    mTextView.invalidate();
                    mTextView.setText(mTextView.getText());
                }
            }
        });
        return urlDrawable;
    }
}
