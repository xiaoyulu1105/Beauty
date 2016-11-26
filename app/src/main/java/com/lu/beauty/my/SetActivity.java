package com.lu.beauty.my;

import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseActivity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by  AngleXiao on 16/11/25.
 * OLiGei  what is your name
 * 轻松拿下一个类 属实有牌面
 */

public class SetActivity extends BaseActivity {


    @Override
    protected int getLayout() {
        return R.layout.activity_set;
    }

    @Override
    protected void initViews() {
        BmobUser bmobUser = new BmobUser();
        bmobUser.setUsername("11");
        bmobUser.setPassword("11");
        Log.d("SetActivity", "bmobUser:" + bmobUser);
        bmobUser.login(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if (e == null) {
                    Toast.makeText(SetActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    //倒计时
                    CountDownTimer timer = new CountDownTimer(1000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            finish();

                        }
                    };
                    timer.start();

                } else {
                    Log.d("SetActivity123", e.getMessage());
                    Toast.makeText(SetActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void initData() {

    }
}