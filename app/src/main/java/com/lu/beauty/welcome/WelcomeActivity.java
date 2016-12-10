package com.lu.beauty.welcome;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.LinearLayout;

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

    private LinearLayout mLinearLayout;

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initViews() {
        mLinearLayout = bindView(R.id.welcome_ll);
    }

    @Override
    protected void initData() {
        // 一定时间自动跳转
        final CountDownTimer timer = new CountDownTimer(3000, 1000) {
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

        // 点击界面, 手动跳转
        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                timer.cancel();
            }
        });

    }
}
