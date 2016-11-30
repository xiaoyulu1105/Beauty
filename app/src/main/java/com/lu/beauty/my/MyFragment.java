package com.lu.beauty.my;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseFragment;
import com.lu.beauty.bean.event.EventQQ;
import com.lu.beauty.tools.CircleDrawable;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.bmob.v3.BmobUser;

/**
 * Created by XiaoyuLu on 16/11/23.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {


    private Button mBtnLogin;
    private ImageButton mBtnSet;
    private TextView mTvName;
    private String mNameQQ;


    @Override
    protected int getLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {
        mBtnLogin = bindView(R.id.btn_my_login);
        mBtnSet = bindView(R.id.btn_my_set);
        mTvName = bindView(R.id.tv_my_name);


//设置圆形头像
        ImageView ivHeadIcon = bindView(R.id.iv_head_icon);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_mine_portrait);

        CircleDrawable circleByZYXDrawable = new CircleDrawable(bitmap);

        ivHeadIcon.setImageDrawable(circleByZYXDrawable);
        setClick(this, mBtnLogin, mBtnSet);
//注册EventBus
        EventBus.getDefault().register(this);

    }


    @Override
    protected void initData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_my_login:

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_my_set:
                Intent intent1 = new Intent(getActivity(), SetActivity.class);
                startActivity(intent1);
                break;

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        BmobUser bmobUser = BmobUser.getCurrentUser();
        if (bmobUser != null) {
            mBtnLogin.setVisibility(View.INVISIBLE);
            mTvName.setVisibility(View.VISIBLE);
            mTvName.setText(bmobUser.getUsername());
        } else {
            mBtnLogin.setVisibility(View.VISIBLE);
            mTvName.setVisibility(View.INVISIBLE);
        }


    }
//    @Subscribe(threadMode = ThreadMode.MAIN)
//     public void onEventQQ(EventQQ eventQQ){
//        mNameQQ = eventQQ.getName();
//        Log.d("MyFragment", mNameQQ);
//
//     }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        mTvName.setText(mNameQQ);
//        Log.d("MyFragment", mNameQQ);
        if (requestCode == 1 && 0 == resultCode && data != null) {

           mNameQQ= data.getStringExtra("name");
            Log.d("MyFragment123", mNameQQ);
          //  icon = data.getStringExtra("icon");
          //  Log.d("MyFragment123", icon);
//            btnLogIn.setVisibility(View.GONE);
//            maNameTv.setVisibility(View.VISIBLE);
//            myDataBtn.setVisibility(View.VISIBLE);
            mTvName.setText(mNameQQ);
        //    VolleySingleton.getInstance().getImage(icon, myIv);

        }



    }
}
