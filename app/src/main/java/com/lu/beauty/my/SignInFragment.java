package com.lu.beauty.my;


import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseFragment;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by  AngleXiao on 16/11/28.
 * OLiGei  what is your name
 * 轻松拿下一个类 属实有牌面
 */

public class SignInFragment extends BaseFragment implements View.OnClickListener {

    private EditText mEtId;
    private EditText mEtPsw;
    private Button mBtnSignIn;

    @Override
    protected int getLayout() {
        return R.layout.fragment_my_signin;
    }

    @Override
    protected void initView() {
        mEtId = bindView(R.id.et_signin_id);
        mEtPsw = bindView(R.id.et_signin_password);
        mBtnSignIn = bindView(R.id.btn_signin_signin);
        setClick(this,mBtnSignIn);

    }

    @Override
    protected void initData() {

    }

    private void signIn() {

        BmobUser bmobUser = new BmobUser();
        String id = mEtId.getText().toString();
        String psw = mEtPsw.getText().toString();
        if (id.equals("") || psw.equals("")){
            Toast.makeText(mContext, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();

        }else if (id.equals(bmobUser.getUsername())){
            Log.d("SignInFragment", bmobUser.getUsername());
            Toast.makeText(mContext, "用户名已经注册过", Toast.LENGTH_SHORT).show();

        }

        else{

            bmobUser.setUsername(id);
            bmobUser.setPassword(psw);

            bmobUser.signUp(new SaveListener<BmobUser>() {
                @Override
                public void done(BmobUser bmobUser, BmobException e) {
                  if (e != null){
                      CountDownTimer timer = new CountDownTimer(1000,1000) {
                          @Override
                          public void onTick(long millisUntilFinished) {

                          }

                          @Override
                          public void onFinish() {
                            getActivity().onBackPressed();

                          }
                      };
                      timer.start();
                      Toast.makeText(getActivity(), "注册成功,请返回上级界面登录", Toast.LENGTH_SHORT).show();
                  }else {
                      Toast.makeText(getActivity(), "注册失败", Toast.LENGTH_SHORT).show();

                  }

                }
            });
        }

    }

    @Override
    public void onClick(View v) {
        signIn();

    }
}
