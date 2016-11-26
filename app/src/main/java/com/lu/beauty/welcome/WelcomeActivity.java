package com.lu.beauty.welcome;

import android.content.Intent;
import android.os.CountDownTimer;

import com.lu.beauty.MainActivity;
import com.lu.beauty.R;
import com.lu.beauty.base.BaseActivity;

import cn.bmob.v3.Bmob;

/**
 * Created by  AngleXiao on 16/11/24.
 * OLiGei  what is your name
 * 轻松拿下一个类 属实有牌面
 */

public class WelcomeActivity extends BaseActivity {
    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initViews() {

}

    @Override
    protected void initData() {
        CountDownTimer timer = new CountDownTimer(2000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();

    }
}
