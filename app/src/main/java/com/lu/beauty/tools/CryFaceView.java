package com.lu.beauty.tools;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.lu.beauty.R;

/**
 * Created by  AngleXiao on 16/11/24.
 * OLiGei  what is your name
 * 轻松拿下一个类 属实有牌面
 */

public class CryFaceView extends RelativeLayout implements View.OnClickListener {

    private Context mContext;
    private Button mButton;
    private Handler mHandler;
    private int mHeight;
    private int tempHeight;
    private View.OnClickListener mOnClickListener;
    private int mDP2PX_first;
    private int mDP2PX_final;

    public int getTempHeight() {
        return tempHeight;
    }

    public void setTempHeight(int tempHeight) {
        this.tempHeight = tempHeight;

        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = tempHeight;
        setLayoutParams(params);
    }


    public CryFaceView(Context context) {
        this(context, null);
    }

    public CryFaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;


        //初始化方法
        init();
    }

    private void init() {
        //初始化
        mHandler = new Handler();
        mButton = new Button(mContext);
        mHandler = new Handler(Looper.getMainLooper());

        //DensityTool的方法把DP转换PX

        mDP2PX_first = DensityTool.dip2px(mContext, 30);
        mDP2PX_final = DensityTool.dip2px(mContext, 250);
        LayoutParams params = new LayoutParams(mDP2PX_first, mDP2PX_first);
        addView(mButton, params);
        //为Button设置监听和动画
        mButton.setOnClickListener(this);
        mButton.setBackgroundResource(R.mipmap.dislike_1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = getHeight();
    }

    @Override
    public void onClick(View v) {

        //获取到组件的高
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        //让组件的高等于临时的高度
        layoutParams.height = tempHeight;
        startAnim();
        //设置
        setLayoutParams(layoutParams);
        setBackground(getResources().getDrawable(R.drawable.shapcolor));
        //设置帧动画
        mButton.setBackground(getResources().getDrawable(R.drawable.animcry));
        //帧动画跑起来
        AnimationDrawable drawable = (AnimationDrawable) mButton.getBackground();

        drawable.start();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stopAnim();
                mButton.setBackgroundResource(R.mipmap.dislike_1);
            }
        }, 30 * 100);

        //属性动画
        ObjectAnimator centerToRight = ObjectAnimator.ofFloat(mButton, "translationX", 0, 10);
        ObjectAnimator rightToLeft = ObjectAnimator.ofFloat(mButton, "translationX", 10, -10);
        ObjectAnimator leftToCenter = ObjectAnimator.ofFloat(mButton, "translationX", -10, 0);

        AnimatorSet set = new AnimatorSet();

        set.play(centerToRight).with(rightToLeft);
        set.play(leftToCenter).after(rightToLeft);
        set.setDuration(500);
        set.start();
//点击判断
        if (mOnClickListener != null) {
            mOnClickListener.onClick(v);
        }
    }

    //停止
    private void stopAnim() {
        //可以让Button点击
        mButton.setEnabled(true);
        ObjectAnimator animator = ObjectAnimator.ofInt(this, "TempHeight", mDP2PX_final, mDP2PX_first);
        animator.start();

    }
    //开始

    private void startAnim() {
        mButton.setEnabled(false);
        ObjectAnimator animator = ObjectAnimator.ofInt(this, "TempHeight", mDP2PX_first, mDP2PX_final);
        animator.start();

    }

    //是OnClickListener的set方法 因为和监听重名了所以自己改为addOnClickListener
    public void addOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }
}
