package com.lu.beauty.my;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseFragment;
import com.lu.beauty.tools.CircleByZYXDrawable;

/**
 * Created by XiaoyuLu on 16/11/23.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {


    private Button mBtnLogin;


    @Override
    protected int getLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {
        mBtnLogin = bindView(R.id.btn_login);

//设置圆形头像
        ImageView ivHeadIcon = bindView(R.id.iv_head_icon);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_mine_portrait);

        CircleByZYXDrawable circleByZYXDrawable = new CircleByZYXDrawable(bitmap);

        ivHeadIcon.setImageDrawable(circleByZYXDrawable);
      setClick(this,mBtnLogin);

    }



    @Override
    protected void initData() {

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(),LoginActivity.class);
        startActivity(intent);
    }
}
