package com.lu.beauty.article;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

/**
 * Created by XiaoyuLu on 16/12/3.
 * 实现画报的 第二级的 顶端的 相对布局的 显示和隐藏
 * 需要在其构造方法里 传入一个 RelativeLayout 的对象, 不需要了.
 */

public class ArticleGestureDetectorListener implements GestureDetector.OnGestureListener{

    private VelocityTracker mVelocityTracker;
    private boolean mIsShowing = true;
    private View mRelativeLayout;


    public ArticleGestureDetectorListener(View relativeLayout) {
        mRelativeLayout = relativeLayout;
    }

    @Override
    public boolean onDown(MotionEvent e) {

        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        // 当手向下滑动, 及查看上面的数据时, 显示 顶端的相对布局
        // 选择 10 是 为了防止 误滑

        if (distanceY > 10 && !mIsShowing) {
            // 显示 顶端数据
            mRelativeLayout.animate().translationY(0)
                    .setInterpolator(new AccelerateDecelerateInterpolator());
            mIsShowing = true;
        }

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        // 根据测试, 我推测
        // velocityY: 计算的是点击和抬起后的平均值

        // 加上下面这行代码 就报错, NullPointException(空指针异常)
        // mVelocityTracker.computeCurrentVelocity(1000);

        if (velocityY < -50 && mIsShowing) {
            // 隐藏 顶端数据
            mRelativeLayout.animate().translationY(-mRelativeLayout.getHeight())
                    .setInterpolator(new AccelerateDecelerateInterpolator());
            mIsShowing = false;

        }

        return false;
    }
}
