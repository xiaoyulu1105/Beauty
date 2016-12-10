package com.lu.beauty.my;


import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseFragment;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
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
    private Button mButton;

    @Override
    protected int getLayout() {
        return R.layout.signin_signin;
    }

    @Override
    protected void initView() {
        mEtId = bindView(R.id.et_signin_id);
        mEtPsw = bindView(R.id.et_signin_password);
        mBtnSignIn = bindView(R.id.btn_signin_signin);
        mButton = bindView(R.id.btn_signin_back);

        setClick(this, mBtnSignIn, mButton);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signin_back:
                getActivity().onBackPressed();

                break;
            case R.id.btn_signin_signin:
                signIn();

                break;
            default:
                break;
        }

    }

    private void signIn() {
        final String id = mEtId.getText().toString();
        final String psw = mEtPsw.getText().toString();

        if (id.equals("") || psw.equals("")) {
            Toast.makeText(mContext, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();

        } else {

            // 以下代码是查询过 数据库后 的 注册, 但是 报红线了.
            BmobQuery<AttentionUser> query = new BmobQuery<>();
            query.addWhereEqualTo("username", id); // 查询当前用户的所有关注的 设计师
            query.findObjects(new FindListener<AttentionUser>() {
                @Override
                public void done(List<AttentionUser> list, BmobException e) {
                    if (e == null) {
                        Log.d("DesignerAllAdapter", "查询数据库成功");

                        if (list.size() > 0) {
                            // 这个账号已经注册过了
                            Toast.makeText(mContext, "用户名被注册过 换一个吧", Toast.LENGTH_SHORT).show();

                        } else {
                            // 账号没有被注册过
                            goTOSign(id, psw);
                        }

                    } else {
                        Log.d("DesignerAllAdapter", "查询数据库失败");
                    }
                }
            });

        }

    }

    /**
     * 直接注册
     * @param id
     * @param psw
     */
    private void goTOSign(String id, String psw) {
        AttentionUser bmobUser = new AttentionUser();
        bmobUser.setUsername(id);
        bmobUser.setPassword(psw);

        bmobUser.signUp(new SaveListener<AttentionUser>() {
            @Override
            public void done(AttentionUser attentionUser, BmobException e) {
                if (e == null) {
                    CountDownTimer timer = new CountDownTimer(1000, 1000) {
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

                } else {
                    Toast.makeText(getActivity(), "注册失败 人品啊 嘻嘻嘻", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
