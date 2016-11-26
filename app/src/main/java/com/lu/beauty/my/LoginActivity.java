package com.lu.beauty.my;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseActivity;

/**
 * Created by  AngleXiao on 16/11/25.
 * OLiGei  what is your name
 * 轻松拿下一个类 属实有牌面
 */
public class LoginActivity extends BaseActivity{
    @Override
    protected int getLayout() {
       return 0;
    }

    @Override
    protected void initViews() {

        LoginFragment loginFragment = new LoginFragment();
        LoginModel loginModel = new LoginModel();
        LoginPresenter loginPresenter = new LoginPresenter(loginFragment,loginModel);
        loginFragment.setPresenter(loginPresenter);
        loginModel.setPresenter(loginPresenter);

        getSupportFragmentManager().beginTransaction().add(android.R.id.content,loginFragment).commit();



    }

    @Override
    protected void initData() {

    }
}
