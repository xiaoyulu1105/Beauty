package com.lu.beauty.my;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseFragment;
import com.lu.beauty.tools.CircleByZYXDrawable;

/**
 * Created by XiaoyuLu on 16/11/23.
 */
public class MyFragment extends BaseFragment {
    @Override
    protected int getLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {

     ImageView ivHeadIcon = bindView(R.id.iv_head_icon);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_mine_portrait);

        CircleByZYXDrawable circleByZYXDrawable = new CircleByZYXDrawable(bitmap);

        ivHeadIcon.setImageDrawable(circleByZYXDrawable);

    }

    @Override
    protected void initData() {

    }
}
