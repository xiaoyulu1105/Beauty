package com.lu.beauty.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * Created by  AngleXiao on 16/11/24.
 * OLiGei  what is your name
 * 轻松拿下一个类 属实有牌面
 * 画圆
 */

public class CircleByZYXDrawable extends Drawable{

    private Bitmap mBitmap;
    private Paint mPaint;
    private int r;


    public CircleByZYXDrawable(Bitmap bitmap) {
        mBitmap = bitmap;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setAntiAlias(true);//抗锯齿
// ****着色器
        BitmapShader shader = new BitmapShader(bitmap
                , Shader.TileMode.CLAMP//图片重复时使用的模式
                , Shader.TileMode.CLAMP);
        mPaint.setShader(shader);//设置画笔的花纹

        //半径
       r = Math.min(bitmap.getHeight()/2,bitmap.getWidth()/2);

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(mBitmap.getWidth()/2,mBitmap.getHeight()/2,r,mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
     mPaint.setAntiAlias(true);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }

    @Override
    public int getIntrinsicHeight() {
        return mBitmap.getHeight();
    }

    @Override
    public int getIntrinsicWidth() {
        return mBitmap.getWidth();
    }
}
