package com.lu.beauty.my;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseActivity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by  AngleXiao on 16/11/25.
 * OLiGei  what is your name
 * 轻松拿下一个类 属实有牌面
 */

public class SetActivity extends BaseActivity implements View.OnClickListener {


    private LinearLayout mLlLogOut;
    private Button mBtnBack;

    @Override
    protected int getLayout() {
        return R.layout.activity_set;
    }

    @Override
    protected void initViews() {
        mLlLogOut = bindView(R.id.ll_set_logout);
        mBtnBack = bindView(R.id.btn_set_back);
        setClick(this, mLlLogOut, mBtnBack);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_set_logout:
                BmobUser.logOut();
                mLlLogOut.setVisibility(View.VISIBLE);
                finish();
                break;
            case R.id.btn_set_back:
                finish();
                break;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        BmobUser bmobUser = BmobUser.getCurrentUser();
        if (bmobUser != null) {
            mLlLogOut.setVisibility(View.INVISIBLE);

        } else {
            mLlLogOut.setVisibility(View.VISIBLE);

        }


    }
}