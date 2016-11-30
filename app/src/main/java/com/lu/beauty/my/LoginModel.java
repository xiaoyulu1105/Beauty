package com.lu.beauty.my;


import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by dllo on 16/11/22.
 */

public class LoginModel implements LoginContract.Model {
    private LoginContract.Presenter mPresenter;
    private Handler mHandler;
    private Context mContext;

    public void setContext(Context context) {
        mContext = context;
    }

    public LoginModel() {
        //线程切换
        mHandler = new Handler(Looper.getMainLooper());
    }


    /**
     * 把 P层放到 M层
     *
     * @param presenter
     */
    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    /**
     * 进行登录(耗时操作)
     *
     * @param userName 用户名
     * @param psw      密码
     */
    @Override
    public void login(final String userName, final String psw) {


        Log.d("LoginModel", Thread.currentThread().getName());
        BmobUser bmobUser = new BmobUser();
        bmobUser.setUsername(userName);
        bmobUser.setPassword(psw);
        Log.d("LoginModel", "bmobUser:" + bmobUser.getUsername());

        bmobUser.login(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {

                if (e == null) {
                    Log.d("LoginModel", "ddd");
                    mPresenter.loginSuccess();

                } else {
                    mPresenter.loginError(e);

                }
            }
        });

    }

    @Override
    public void qqLogin() {
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        qq.authorize();
        qq.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                PlatformDb platformDb = platform.getDb();
                String name = platformDb.getUserName();
                String icon = platformDb.getUserIcon();

                Intent intent = new Intent();
                intent.putExtra("name",name);
                intent.putExtra("icon",icon);
                



            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        });

    }


}
