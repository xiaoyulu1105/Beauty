package com.lu.beauty.my;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.lu.beauty.R;
import com.lu.beauty.base.BaseFragment;
import com.lu.beauty.bean.event.EventQQ;
import com.lu.beauty.tools.CircleDrawable;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import cn.bmob.v3.BmobUser;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

import static android.R.attr.data;

/**
 * Created by XiaoyuLu on 16/11/23.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {


    private Button mBtnLogin;
    private ImageButton mBtnSet;
    private TextView mTvName;
    private String mNameQQ;

    private ImageView mIvHeadIcon;
    private Platform mQq;
    private LinearLayout mLlFavorate;
    // private ImageView mIvIcon;


    @Override
    protected int getLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {
        mBtnLogin = bindView(R.id.btn_my_login);
        mBtnSet = bindView(R.id.btn_my_set);
        mTvName = bindView(R.id.tv_my_name);
        mLlFavorate = bindView(R.id.ll_my_favorate);
      //  mIvIcon = bindView(R.id.iv_head_icon);


//设置圆形头像
        mIvHeadIcon = bindView(R.id.iv_head_icon);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_mine_portrait);

        CircleDrawable circleByZYXDrawable = new CircleDrawable(bitmap);

        mIvHeadIcon.setImageDrawable(circleByZYXDrawable);

        setClick(this, mBtnLogin, mBtnSet,mLlFavorate);

////注册EventBus
     //  EventBus.getDefault().register(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        initData();

    }
    @Override
    protected void initData() {
        mQq = ShareSDK.getPlatform(QQ.NAME);
        try {
            PlatformDb platformDb = mQq.getDb();
           String name = platformDb.getUserName();
           String  icon = platformDb.getUserIcon();



            if (TextUtils.isEmpty(name)) {
                //退出登录
                mBtnLogin.setVisibility(View.VISIBLE);
                mTvName.setVisibility(View.INVISIBLE);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_mine_portrait);

                CircleDrawable circleByZYXDrawable = new CircleDrawable(bitmap);

                mIvHeadIcon.setImageDrawable(circleByZYXDrawable);
                return;
            }

//           String id =  platformDb.getUserId();
//            Log.d("MyFragment", id);


            if (!TextUtils.isEmpty(name)) {
                mBtnLogin.setVisibility(View.GONE);
                mTvName.setVisibility(View.VISIBLE);
               // myDataBtn.setVisibility(View.VISIBLE);
                mTvName.setText(name);

                Glide.with(this).load(icon).asBitmap().into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        CircleDrawable circleDrawable = new CircleDrawable(resource);
                        mIvHeadIcon.setImageDrawable(circleDrawable);
                    }
                });

            }

        } catch (NullPointerException e) {

        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_my_login:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent,1);
                break;
            case R.id.btn_my_set:
                Intent intent1 = new Intent(getActivity(), SetActivity.class);
                startActivityForResult(intent1,1);
                break;
            case R.id.ll_my_favorate:
                Intent intent2 = new Intent(getActivity(),FavorateDesignerActivity.class);
                startActivity(intent2);
                break;

        }
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        BmobUser bmobUser = BmobUser.getCurrentUser();
//        if (bmobUser != null) {
//            mBtnLogin.setVisibility(View.INVISIBLE);
//            mTvName.setVisibility(View.VISIBLE);
//            mTvName.setText(bmobUser.getUsername());
//
//        } else {
//
//            mBtnLogin.setVisibility(View.VISIBLE);
//            mTvName.setVisibility(View.INVISIBLE);
//        }
//    }


  //  }
//    @Subscribe(threadMode = ThreadMode.MAIN)
//     public void onEventQQ(EventQQ eventQQ){
//        mNameQQ = eventQQ.getName();
//        Log.d("MyFragment", mNameQQ);
//        mTvName.setText(mNameQQ);
//
// }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (data == null) {
//            //退出登录
//            mBtnLogin.setVisibility(View.VISIBLE);
//            mTvName.setVisibility(View.INVISIBLE);
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_mine_portrait);
//
//            CircleDrawable circleByZYXDrawable = new CircleDrawable(bitmap);
//
//            mIvHeadIcon.setImageDrawable(circleByZYXDrawable);
//            return;
//        }
//
//        if (requestCode == 1 && 0 == resultCode && data != null) {
//
//            mNameQQ= data.getStringExtra("name");
//            String iconQQ = data.getStringExtra("icon");
//            Log.d("MyFragment123", mNameQQ);
//            mBtnLogin.setVisibility(View.GONE);
//            mTvName.setVisibility(View.VISIBLE);
//          //  mBtnLogin.setVisibility(View.VISIBLE);
//            mTvName.setText(mNameQQ);
//           // VolleySingleton.getInstance().getImage(icon, myIv);
//
//            Glide.with(this).load(iconQQ).asBitmap().into(new SimpleTarget<Bitmap>() {
//                @Override
//                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                  CircleDrawable circleDrawable = new CircleDrawable(resource);
//                    mIvHeadIcon.setImageDrawable(circleDrawable);
//                }
//            });
//
//        }
//    }


}
