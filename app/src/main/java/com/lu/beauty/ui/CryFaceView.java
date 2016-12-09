package com.lu.beauty.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
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
import com.lu.beauty.tools.DensityTool;

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
    //
    private int mDP2PX_first; // 最初的高度
    private int mDP2PX_final = -1; // 最终的高度
    private int mDP_first = 30;  // 表情的高度, 单位dp.
    private int mDP_default = 150; // 默认高度

    private boolean isChange = false; // 判断表情是否处于变化状态, 默认不是

    // by 小玉
    private  boolean isSelected = false; // 用一个静态变量来判断是否选择了 哭脸
    private int myBackgroundDrawable = R.drawable.shap;  // 哭脸的默认背景色为白色
    private SmileFaceView mSmileFaceView;

    // 设置 isSelected 的 set get方法
    public  boolean getIsSelected() {
        return isSelected;
    }
    public  void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    // 设置 myBackgroundDrawable 的 set get 方法
    public  int getMyBackgroundDrawable() {
        return myBackgroundDrawable;
    }
    public void setMyBackgroundDrawable(int myBackgroundDrawable) {
        this.myBackgroundDrawable = myBackgroundDrawable;
        // 刷新 UI
        this.invalidate();
    }

    // 对象 mSmileFaceView 的set方法
    public void setSmileFaceView(SmileFaceView smileFaceView) {
        Log.d("CryFaceViewSet", "setSmileFaceView---参数的smile对象" + smileFaceView);
        mSmileFaceView = smileFaceView;
        Log.d("CryFaceViewSet", "setSmileFaceView---哭脸类中的smile对象" + mSmileFaceView);
    }

    public boolean isChange() {
        return isChange;
    }

    // 为最终的高度设置set方法, 添加 by 小玉 已实现
    // 在 ProductListViewAdapter 中调用该set方法, 设置最终高度
    public void setDP2PX_final(int DP2PX_final) {
        mDP2PX_final = DP2PX_final;
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
        //
        setBackground(getResources().getDrawable(myBackgroundDrawable));

        //初始化
        mHandler = new Handler();
        mButton = new Button(mContext);
        mHandler = new Handler(Looper.getMainLooper()); // 用来计算帧动画的按时结束

        // DensityTool的方法把DP转换PX

        mDP2PX_first = DensityTool.dip2px(mContext, mDP_first);
        if (mDP2PX_final == -1) {
            // 当未动态设置高度成功, 默认高度为150
            mDP2PX_final = DensityTool.dip2px(mContext, mDP_default);
        }


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

        Log.d("CryFaceViewOnclick", "点击了哭脸");

        // 如果笑脸是选中状态
//        if (mSmileFaceView.getIsSelected()) {
//
//            Log.d("CryFaceView", "笑脸被选择过了" + mSmileFaceView); // 有打印
//            // 将笑脸变成白色
//            // TODO 在 ProductListViewAdapter 里不能监听到 哭脸的点击事件, why?
//            mSmileFaceView.setMyBackgroundDrawable(R.drawable.shap); // new的没用,不能对应
//            mSmileFaceView.setIsSelected(false);
//        }
        isSelected = true;

        //获取到组件的高
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        //让组件的高等于临时的高度
        layoutParams.height = tempHeight;

        //设置背景颜色变黄色
        setLayoutParams(layoutParams);
        setBackground(getResources().getDrawable(R.drawable.shapcolor));
        // 开始拉伸的属性动画
        startAnim();

        //设置帧动画
        mButton.setBackground(getResources().getDrawable(R.drawable.animcry));
        //帧动画跑起来
        AnimationDrawable drawable = (AnimationDrawable) mButton.getBackground();
        drawable.start();

        // 延时 2000 后进行收缩的属性动画
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 进行收缩的属性动画
                stopAnim();
                mButton.setBackgroundResource(R.mipmap.dislike_1);
            }
        }, 20 * 100);

        // 属性动画, 实现左右摇头
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

    // 停止
    private void stopAnim() {

        //可以让Button可以点击
        mButton.setEnabled(true);

        ObjectAnimator animator = ObjectAnimator.ofInt(this, "TempHeight", mDP2PX_final, mDP2PX_first);
        animator.start();

        // 动画结束, 布尔值为false
        isChange = false;

        //
    }

    // 开始
    private void startAnim() {
        mButton.setEnabled(false);
        ObjectAnimator animator = ObjectAnimator.ofInt(this, "TempHeight", mDP2PX_first, mDP2PX_final);
        animator.start();

        // 开始上升动画时, 布尔值为true
        isChange = true;
    }

    //是OnClickListener的set方法 因为和监听重名了所以自己改为addOnClickListener
    public void addOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }
}
