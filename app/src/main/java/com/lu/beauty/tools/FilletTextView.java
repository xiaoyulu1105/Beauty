package com.lu.beauty.tools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * If the operation is no problem, it is written by wangqiaosheng
 * , otherwise it is written by zhouyunxiao
 */

public class FilletTextView extends TextView{

    private Paint paint;
    private int mWidth, mHeight; // 组件的宽高

    public FilletTextView(Context context) {
        super(context);
    }

    public FilletTextView(Context context, AttributeSet attars) {
        super(context);
        init();
    }

    private void init() {

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0xffffff);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        mWidth = getWidth();
        mHeight = getHeight();

        RectF rectF = new RectF(0, 0, mWidth, mHeight);
        canvas.drawRoundRect(rectF, mHeight / 2, mHeight / 2, paint);
        super.onDraw(canvas);

    }

















}
