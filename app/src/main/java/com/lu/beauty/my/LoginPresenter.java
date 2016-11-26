package com.lu.beauty.my;

import android.text.TextUtils;

/**
 * Created by dllo on 16/11/22.
 * P层
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;
    private LoginContract.Model mModel;

    public LoginPresenter(LoginContract.View mView, LoginContract.Model mModel) {
        this.mView = mView;
        this.mModel = mModel;
    }

    /**
     * 接收View层传递过来的 用户名 密码
     *
     * @param userName 用户名
     * @param psw      密码
     */
    @Override
    public void login(String userName, String psw) {
        if (checkIsEmpty(userName, psw)) {
            mView.showEmptyMsg();
        } else {
            mModel.login(userName, psw);
            mView.showLoading();
        }
    }

    /**
     * 检测用户名或者密码是否为空
     *
     * @param userName 用户名
     * @param psw      密码
     * @return true 为空 , false 不为空
     */
    @Override
    public boolean checkIsEmpty(String userName, String psw) {
        return TextUtils.isEmpty(userName) || TextUtils.isEmpty(psw);
    }

    /**
     * 登录成功
     */
    @Override
    public void loginSuccess() {
        mView.loginSuccess();
    }

    /**
     * 登录失败
     *
     * @param exception 登录失败的异常信息
     */
    @Override
    public void loginError(Exception exception) {
        if (exception == null) {
            mView.loginError("登录失败");
        } else {
            mView.loginError(exception.getMessage());
        }
    }
}
