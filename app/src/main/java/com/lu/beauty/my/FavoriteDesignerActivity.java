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

import com.google.gson.Gson;
import com.lu.beauty.R;
import com.lu.beauty.base.BaseActivity;
import com.lu.beauty.designer.AttentionSingleBean;
import com.lu.beauty.designer.AttentionUser;
import com.lu.beauty.designer.Collections;

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

    private List<Collections> mCollections;
    private ArrayList<AttentionSingleBean> mAttentionSingleBeen;

    private String mAttentionName;
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
        mCollections = new ArrayList<>();
        mAttentionSingleBeen = new ArrayList<>();

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

            ArrayList<String> arrayList = new ArrayList<>();
            int getAttentionCount;

            arrayList = attentionUser.getAttentionList();
            getAttentionCount = attentionUser.getAttentionCount();
            Log.d("FavoriteDesignerActivit", "关注的设计师个数: " + getAttentionCount);

            if (getAttentionCount == 0) {
                Log.d("FavorateDesignerActivit", "该用户还未关注设计师");
                Toast.makeText(this, "快去关注吧", Toast.LENGTH_SHORT).show();

            } else {
                for (int j = 0; j < arrayList.size(); j++) {
                    String name = arrayList.get(j);
//                    try {
//                        JSONObject jsonObject = new JSONObject(name);
//                        JSONArray jsonArray = jsonObject.getJSONArray("mAttentionSingleBeen");
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            JSONObject json = jsonArray.getJSONObject(i);
//                            mAttentionName = json.getString("attentionName");
//                            String attentionLabel = json.getString("attentionLabel");
//                            String attentionImage = json.getString("attentionImage");
//                            String attentionAvatar = json.getString("attentionAvatar");
//                            String attentionId = json.getString("attentionId");
//
//                            AttentionUser attentionUser1 = new AttentionUser();
//                            attentionUser1.setAttentionName(mAttentionName);
//                            attentionUser1.setAttentionLabel(attentionLabel);
//                            attentionUser1.setAttentionImage(attentionImage);
//                            attentionUser1.setAttentionAvatar(attentionAvatar);
//                            attentionUser1.setAttentionId(attentionId);
//
//                            mAttentionSingleBeen.add(attentionUser1);
//
//                        }
//                        mFavoriteAdapter = new FavoriteAdapter();
//
//                        mFavoriteAdapter.setAttentionUsers(mAttentionSingleBeen);
//                        mLvFavorite.setAdapter(mFavoriteAdapter);
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }

                    // 用 Gson解析
                    Collections collections = new Gson().fromJson(name, Collections.class);
                    AttentionSingleBean attentionSingleBean = collections.getAttentionSingleBeens().get(0);
                    mAttentionSingleBeen.add(attentionSingleBean);

                    mFavoriteAdapter = new FavoriteAdapter();
                    mFavoriteAdapter.setAttentionSingleBeens(mAttentionSingleBeen);
                    mLvFavorite.setAdapter(mFavoriteAdapter);

                }
            }

        } else {
            // 处于未登录状态
            Log.d("FavoriteDesignerActivit", "请先登录");
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
            FavoriteDesignerActivity.this.finish();

            Intent intent = new Intent(FavoriteDesignerActivity.this, LoginActivity.class);
            startActivity(intent);
        }

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

