package com.lu.beauty.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lu.beauty.R;
import com.lu.beauty.product.TestListener;
import com.lu.beauty.tools.DensityTool;

/**
 * Created by  AngleXiao on 16/11/24.
 * OLiGei  what is your name
 * 轻松拿下一个类 属实有牌面
 */
// 新建一个包 ui包 或者叫 widget,自定义组件都放到这里面
public class SmileFaceView extends RelativeLayout implements View.OnClickListener {

    private Context mContext;
    private Button mButton;
    private View.OnClickListener mOnClickListener;
    private Handler mHandler;
    private int mHeight;

    private int tempHeight;
    private int mDP2PX_60;
    private int mMDP2PX_FIRST;
    private int mDP2PX_FINAL = -1; // 最终的高度, 赋值-1是用于下面的判断

    // by 小玉
    public  boolean isSelected = false; // 用一个静态变量来判断是否选择了 笑脸
    private int myBackgroundDrawable = R.drawable.shap;  // 笑脸的默认背景色为白色
    private CryFaceView mCryFaceView;

    // 设置 isSelected 的 set get方法
    public  boolean getIsSelected() {
        return isSelected;
    }
    public  void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    // 设置 myBackgroundDrawable 的 set get 方法.
    public int getMyBackgroundDrawable() {
        return myBackgroundDrawable;
    }

    public void setMyBackgroundDrawable(int myBackgroundDrawable) {
        this.myBackgroundDrawable = myBackgroundDrawable;
        // 刷新 UI
        this.invalidate();
    }
    //
    public void setCryFaceView(CryFaceView cryFaceView) {

        Log.d("SmileFaceView", "setCryFaceView---参数的 cry 对象" + cryFaceView);
        this.mCryFaceView = cryFaceView;
        Log.d("SmileFaceView", "setCryFaceView---笑脸类中的 cry 对象");
    }

    // 为最终的高度设置set方法, 添加 by 小玉 已实现
    // 在 ProductListViewAdapter 中调用该set方法, 设置最终高度
    public void setDP2PX_FINAL(int DP2PX_FINAL) {
        mDP2PX_FINAL = DP2PX_FINAL;
    }



    private int getTempHeight() {
        return tempHeight;
    }

    private void setTempHeight(int tempHeight) {

        this.tempHeight = tempHeight;

        ViewGroup.LayoutParams params = getLayoutParams();

        params.height = tempHeight;

        setLayoutParams(params);

    }

    public SmileFaceView(Context context) {
        this(context, null);
    }

    public SmileFaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        //
        setBackground(getResources().getDrawable(myBackgroundDrawable));

        mButton = new Button(mContext);
        mHandler = new Handler(Looper.getMainLooper());

        // 一个工具类,dp转px
        mMDP2PX_FIRST = DensityTool.dip2px(mContext,30); // 圆的直径
        if (mDP2PX_FINAL == -1){
            // 当未动态设置高度成功, 默认高度为150
            mDP2PX_FINAL = DensityTool.dip2px(mContext, 150);
        }

        // 将Button 添加到自定义的相对布局
        LayoutParams layoutParams = new LayoutParams(mMDP2PX_FIRST, mMDP2PX_FIRST);
        addView(mButton, layoutParams);
        mButton.setOnClickListener(this);
        mButton.setBackgroundResource(R.mipmap.like_1);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = getHeight();
    }

    @Override
    public void onClick(View v) {
        Log.d("SmileFaceView", "点击了笑脸");
        // 如果点击笑脸了

        // 如果哭脸是选中状态
        if (mCryFaceView.getIsSelected()) {
            Log.d("SmileFaceView", "哭脸被选择过了");
            // 将哭脸变成白色
            // TODO 想在这里设置哭脸的 背景drawable, 但是 new一个新的哭脸对象没有用
            // TODO 主要是在 ProductListViewAdapter 里不能监听到 哭脸的点击事件, why?
            // TODO 想要能监听到点击事件该怎么办?

            mCryFaceView.setMyBackgroundDrawable(R.drawable.shap);
            mCryFaceView.setIsSelected(false);
        }
        isSelected = true;

        //动画
        //得到布局的高度,然后修改高度,并且给布局加上颜色
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = tempHeight;
        setLayoutParams(params);

        setBackgroundResource(R.drawable.shapcolor);
        startAnim();

        //帧动画
        mButton.setBackgroundResource(R.drawable.anim);
        AnimationDrawable animationDrawable = (AnimationDrawable) mButton.getBackground();
        animationDrawable.start();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                stopAnim();
                mButton.setBackgroundResource(R.mipmap.like_1);

            }
        }, 33 * 100);

        //属性动画
        ObjectAnimator centerToRight = ObjectAnimator.ofFloat(mButton, "translationY", 0, 10);
        ObjectAnimator rightToLeft = ObjectAnimator.ofFloat(mButton, "translationY", 10, -10);
        ObjectAnimator leftToCenter = ObjectAnimator.ofFloat(mButton, "translationY", -10, 0);

        AnimatorSet set = new AnimatorSet();

        set.play(centerToRight).with(rightToLeft);
        set.play(leftToCenter).after(rightToLeft);
        set.setDuration(500);
        set.start();

        if (mOnClickListener != null) {
            mOnClickListener.onClick(v);

        }
    }

    private void stopAnim() {
        mButton.setEnabled(true);
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(this, "tempHeight", mDP2PX_FINAL, mMDP2PX_FIRST);

        objectAnimator.start();

    }

    private void startAnim() {
        mButton.setEnabled(false);
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(this, "tempHeight", mDP2PX_60, mDP2PX_FINAL);
        objectAnimator.start();
    }

    public void addOnClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

}
