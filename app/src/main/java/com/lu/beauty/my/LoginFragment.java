package com.lu.beauty.my;


import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseFragment;

/**
 * Created by  AngleXiao on 16/11/25.
 * OLiGei  what is your name
 * 轻松拿下一个类 属实有牌面
 */

public class LoginFragment extends BaseFragment implements LoginContract.View, View.OnClickListener {
    private LoginContract.Presenter mPresenter;
    private ProgressDialog progressDialog;
    private EditText mEtId;
    private EditText mEtPsw;
    private Button mBtnLogin;
    private Button mBtnBack;
    private TextView mTvSignIn;

    @Override
    protected int getLayout() {
        return R.layout.item_login;
    }

    @Override
    protected void initView() {
        mEtId = bindView(R.id.et_login_id);
        mEtPsw = bindView(R.id.et_login_password);
        mBtnLogin = bindView(R.id.btn_login_login);
        mBtnBack = bindView(R.id.btn_login_back);
        mTvSignIn = bindView(R.id.btn_login_signin);
        setClick(this, mBtnLogin, mBtnBack, mTvSignIn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_login:
                String etId = mEtId.getText().toString();
                String etPsw = mEtPsw.getText().toString();
                mPresenter.login(etId, etPsw);
                break;
            case R.id.btn_login_back:
                getActivity().onBackPressed();
                break;
            case R.id.btn_login_signin:

                FragmentManager manager = getChildFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.layout, new SignInFragment());
                transaction.commit();
                break;
        }


    }

    @Override
    protected void initData() {

    }


    /**
     * 把P层放到V层里面
     *
     * @param presenter
     */
    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;

    }

    /**
     * 显示出 用户名/密码 为空的提示信息
     */
    @Override
    public void showEmptyMsg() {
        Toast.makeText(getActivity(), "用户名/密码为空", Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示正在登录的画面
     */
    @Override
    public void showLoading() {
        progressDialog = ProgressDialog.show(getActivity(), "正在登录", "正在dl");

    }

    /**
     * 登录成功
     */
    @Override
    public void loginSuccess() {
        progressDialog.dismiss();
        Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();
        CountDownTimer timer = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                getActivity().finish();

            }
        };
        timer.start();

    }

    /**
     * 登录失败 显示失败信息
     *
     * @param msg 失败信息
     */
    @Override
    public void loginError(String msg) {
        progressDialog.dismiss();
        Toast.makeText(getActivity(),"用户名/密码输入有误", Toast.LENGTH_SHORT).show();
    }


}
