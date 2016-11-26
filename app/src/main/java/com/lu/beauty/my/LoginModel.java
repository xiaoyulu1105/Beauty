package com.lu.beauty.my;


import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

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

        BmobUser bmobUser = new BmobUser();
        bmobUser.setUsername(userName);
        bmobUser.setPassword(psw);

        bmobUser.login(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if (e == null) {
                    mPresenter.loginSuccess();

                    CountDownTimer timer = new CountDownTimer(1000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }
                        @Override
                        public void onFinish() {

                        }
                    };
                    timer.start();

                } else {
                    mPresenter.loginError(e);
                }
            }
        });


//         new Thread(new Runnable() {
//             @Override
//             public void run() {
//                 try {
//                     Thread.sleep(3000);
//                     if ("111111".equals(userName)&&"222222".equals(psw)){
//                         mHandler.post(new Runnable() {
//                             @Override
//                             public void run() {
//                                 mPresenter.loginSuccess();
//
//                             }
//                         });
//                     }else {
//                         final Exception exception = new Exception("用户名/密码错误");
//                         mHandler.post(new Runnable() {
//                             @Override
//                             public void run() {
//                                 mPresenter.loginError(exception);
//
//                             }
//                         });
//                     }
//                 } catch (InterruptedException e) {
//                     e.printStackTrace();
//                 }
//             }
//         }).start();

    }
}
