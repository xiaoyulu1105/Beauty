package com.lu.beauty.my;

import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.lu.beauty.R;
import com.lu.beauty.base.BaseActivity;
import com.lu.beauty.tools.CircleDrawable;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by  AngleXiao on 16/11/25.
 * OLiGei  what is your name
 * 轻松拿下一个类 属实有牌面
 */

public class SetActivity extends BaseActivity implements View.OnClickListener {


    private LinearLayout mLlLogOut;
    private ImageButton mBtnBack;
    private String mIcon;

    private PlatformActionListener mPlatformActionListener;
    private ImageView mIvIcon;

    @Override
    protected int getLayout() {
        return R.layout.activity_set;
    }

    @Override
    protected void initViews() {
        mLlLogOut = bindView(R.id.ll_set_logout);
        mBtnBack = bindView(R.id.btn_set_back);
        mIvIcon = bindView(R.id.iv_set_icon);
        setClick(this, mLlLogOut, mBtnBack);
        Glide.with(this).load(mIcon).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                CircleDrawable circleDrawable = new CircleDrawable(resource);
                mIvIcon.setImageDrawable(circleDrawable);
            }
        });
    }
    @Override
    protected void initData() {
//输出所有授权信息
        mPlatformActionListener = new PlatformActionListener() {


            @Override
             public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                 //输出所有授权信息
                 platform.getDb().exportData();
                mIcon = platform.getDb().getUserIcon();


             }

             @Override
             public void onError(Platform platform, int i, Throwable throwable) {
                 throwable.printStackTrace();
             }

             @Override
             public void onCancel(Platform platform, int i) {

             }
         };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_set_logout:

                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                if (qq.isAuthValid()) {
                    qq.removeAccount(true);
                }
                BmobUser.logOut();
              //  qq.setPlatformActionListener(mPlatformActionListener);
//authorize与showUser单独调用一个即可
//               qq.authorize();//单独授权，OnComplete返回的hashmap是空的
//                qq.showUser(null);//授权并获取用户信息
//isValid和removeAccount不开启线程，会直接返回。

               setResult(-1);

                finish();

                break;
            case R.id.btn_set_back:
                onBackPressed();
                break;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

//        BmobUser bmobUser = BmobUser.getCurrentUser();
//        if (bmobUser != null) {
//            mLlLogOut.setVisibility(View.VISIBLE);
//
//        } else {
//            mLlLogOut.setVisibility(View.INVISIBLE);
//
//        }
    }


}