package com.lu.beauty.my;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseActivity;
import com.lu.beauty.designer.AttentionUser;
import com.lu.beauty.designer.MyUser;

import java.util.ArrayList;
import java.util.List;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

/**
 * Created by  AngleXiao on 16/12/3.
 * OLiGei  what is your name
 * 轻松拿下一个类 属实有牌面
 */
public class FavoriteDesignerActivity extends BaseActivity implements View.OnClickListener, SwipeBackActivityBase {




    private TextView mTextView;
    private FavoriteAdapter mFavoriteAdapter;
    private ListView mLvFavorite;
    private ImageView mIvBack;
    private SwipeBackActivityHelper swipeBackActivityHelper;

    @Override
    protected int getLayout() {
        return R.layout.activity_favoratedesiner;
    }

    @Override
    protected void initViews() {
        mLvFavorite = bindView(R.id.lv_favorate);
        mIvBack = bindView(R.id.favorate_back);



        mTextView = bindView(R.id.bmobtest);
        setClick(this, mIvBack);

    }

    @Override
    protected void initData() {
        swipeBackActivityHelper = new SwipeBackActivityHelper(this);
        swipeBackActivityHelper.onActivityCreate();



    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }

    /**
     * 复写 SwipeBackActivityBase接口的三个抽象方法:
     * getSwipeBackLayout, setSwipeBackEnable, scrollToFinishActivity.
     * 此外还需要手动复写另外两个方法: onPostCreate, findViewById
     */
    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        // 修改了返回值
        return swipeBackActivityHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        // 添加一行代码
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        swipeBackActivityHelper.onPostCreate();
    }

    @Override
    public View findViewById(@IdRes int id) {
        View v = super.findViewById(id);
        if (v == null && swipeBackActivityHelper != null) {
            return swipeBackActivityHelper.findViewById(id);
        }
        return v;
    }
}

