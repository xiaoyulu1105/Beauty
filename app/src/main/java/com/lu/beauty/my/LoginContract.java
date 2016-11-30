package com.lu.beauty.my;

/**
 * Created by  AngleXiao on 16/11/26.
 * OLiGei  what is your name
 * 轻松拿下一个类 属实有牌面
 */

public interface LoginContract {

    interface View {
        /**
         * 把P层放到V层里
         *
         * @param presenter
         */
        void setPresenter(Presenter presenter);

        /**
         * 显示出 用户名/密码 为空的提示信息
         */
        void showEmptyMsg();

        /**
         * 正在登录
         */
        void showLoading();

        /**
         * 登录成功
         */
        void loginSuccess();

        /**
         * 登录失败
         *
         * @param msg 失败信息
         */
        void loginError(String msg);

    }


    interface Presenter {
        /**
         * 接收V层传过来的用户名和密码
         * @param useName
         * @param psw
         */
        void login(String useName,String psw);


        /**
         * 检测用户名或者密码是否为空
         * @param userName 用户名
         * @param psw 密码
         * @return true 为空 , false 不为空
         */
        boolean checkIsEmpty(String userName, String psw);

        /**
         * 登录成功
         */
        void loginSuccess();

        /**
         * 登录失败
         * @param exception 登录失败的异常信息
         */
        void loginError(Exception exception);

        /**
         * QQ登录
         */
        void qqLogin();

    }


    interface Model {
        /**
         * 把P层放到M层里
         *
         * @param presenter
         */
        void setPresenter(Presenter presenter);

        /**
         * 进行登录
         * @param useName 用户名
         * @param psw 密码
         */
        void login(String useName , String psw);
         void  qqLogin ();






    }



}
