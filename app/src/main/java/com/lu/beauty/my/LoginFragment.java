package com.lu.beauty.my;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseFragment;
import com.lu.beauty.bean.event.EventQQ;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

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

public class LoginFragment extends BaseFragment implements LoginContract.View, View.OnClickListener {
    private LoginContract.Presenter mPresenter;
    private ProgressDialog progressDialog;
    private EditText mEtId;
    private EditText mEtPsw;
    private Button mBtnLogin;
    private Button mBtnBack;
    private TextView mTvSignIn;
    private LinearLayout mllQq;

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
        mllQq = bindView(R.id.ll_login_qq);
        setClick(this, mBtnLogin, mBtnBack, mTvSignIn, mllQq);
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
            case R.id.ll_login_qq:
                mPresenter.qqLogin();
                //mPresenter.signBmob();
                getActivity().onBackPressed();


                //   qq.showUser(null);

//                qq.setPlatformActionListener(new PlatformActionListener() {
//                    @Override
//                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
//                        Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();
//                        PlatformDb platformDb = platform.getDb();
//                        String name = platformDb.getUserId();
//
//                        Toast.makeText(mContext, name, Toast.LENGTH_SHORT).show();
//                        String icon = platformDb.getUserIcon();
//                        Toast.makeText(mContext, platformDb.getUserId(), Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent();
//                        intent.putExtra("name", name);
//                        intent.putExtra("icon", icon);
//                        getActivity().setResult(0, intent);
//                    EventBus.getDefault().post(new EventQQ(name));
//
//                    }
//
//                    @Override
//                    public void onError(Platform platform, int i, Throwable throwable) {
//                        Toast.makeText(mContext, "登录失败", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onCancel(Platform platform, int i) {
//                        Toast.makeText(mContext, "取消", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                Log.d("LoginFragment", "aaaa");
//
//                break;
//
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
