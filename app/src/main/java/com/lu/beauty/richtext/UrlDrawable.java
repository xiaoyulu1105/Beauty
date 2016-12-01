package com.lu.beauty.richtext;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by XiaoyuLu on 16/12/1.
 */
public class UrlDrawable extends BitmapDrawable{
    protected Bitmap mBitmap;

    @Override
    public void draw(Canvas canvas) {
        if (mBitmap != null) {
            canvas.drawBitmap(mBitmap, 0, 0, getPaint());
        }
    }
}
