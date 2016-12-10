package com.lu.beauty.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
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

        AttentionUser attentionUser = AttentionUser.getCurrentUser(AttentionUser.class);

        if (attentionUser != null) {
            Log.d("FavorateDesignerActivit", "已登录状态");

            BmobQuery<Attention> query = new BmobQuery<>();
            query.addWhereEqualTo("myUser", attentionUser); // 查询当前用户的所有关注的设计师
            query.findObjects(new FindListener<Attention>() {
                @Override
                public void done(List<Attention> list, BmobException e) {
                    if (e == null) {
                        Log.d("FavoriteDesignerActivit", "查询数据库表 Attention 成功" + list.size());
                        showQueryData(list);

                    } else {
                        Log.d("FavoriteDesignerActivit", "查询数据库失败");
                    }
                }
            });


        } else {
            // 处于未登录状态
            // 先登录
            loginFirst();

        }

    }

    /**
     * 跳转到登录
     */
    private void loginFirst() {
        Log.d("FavoriteDesignerActivit", "请先登录");
        Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
        FavoriteDesignerActivity.this.finish();

        Intent intent = new Intent(FavoriteDesignerActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * 将查询到的数据进行显示
     * @param list
     */
    private void showQueryData(List<Attention> list) {

        if (list.size() == 0) {
            Toast.makeText(this, "快去关注吧", Toast.LENGTH_SHORT).show();
        }
        // 先将集合倒序
        Collections.reverse(list);

        mFavoriteAdapter = new FavoriteAdapter();
        mFavoriteAdapter.setAttentions((ArrayList<Attention>) list);
        mLvFavorite.setAdapter(mFavoriteAdapter);
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

