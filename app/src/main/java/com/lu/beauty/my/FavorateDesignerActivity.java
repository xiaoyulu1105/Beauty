package com.lu.beauty.my;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseActivity;
import com.lu.beauty.designer.AttentionUser;
import com.lu.beauty.designer.Collections;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.a.a.This;

/**
 * Created by  AngleXiao on 16/12/3.
 * OLiGei  what is your name
 * 轻松拿下一个类 属实有牌面
 */
public class FavorateDesignerActivity extends BaseActivity implements View.OnClickListener {

    private List<Collections> mCollectionses;
    private ArrayList<AttentionUser> mAttentionUsers;

    private String mAttentionName;
    private TextView mTextView;
    private FavorateAdapter mFavorateAdapter;
    private ListView mLvFavorate;
    private ImageView mIvBack;

    @Override
    protected int getLayout() {
        return R.layout.activity_favoratedesiner;
    }

    @Override
    protected void initViews() {
        mLvFavorate = bindView(R.id.lv_favorate);
        mIvBack = bindView(R.id.favorate_back);
        mCollectionses = new ArrayList<>();
        mAttentionUsers = new ArrayList<>();


        mTextView = bindView(R.id.bmobtest);
        setClick(this, mIvBack);


//
//
// favorateAdapter.setAttentionUsers(mCollectionses);
//        lvFavorate.setAdapter(favorateAdapter);

    }

    @Override
    protected void initData() {
        AttentionUser attentionUser = AttentionUser.getCurrentUser(AttentionUser.class);
        //BmobUser bmobUser = new BmobUser();
        if (attentionUser != null) {
//            String name = attentionUser.getAttentionName();
            Log.d("FavorateDesignerActivit", "已登录状态");

            ArrayList<String> arrayList = attentionUser.getAttentionList();

            for (int j = 0; j < arrayList.size(); j++) {
                String name = arrayList.get(j);
                try {
                    JSONObject jsonObject = new JSONObject(name);
                    JSONArray jsonArray = jsonObject.getJSONArray("mAttentionUsers");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json = jsonArray.getJSONObject(i);
                        mAttentionName = json.getString("attentionName");
                        String attentionLabel = json.getString("attentionLabel");
                        String attentionImage = json.getString("attentionImage");
                        String attentionAvatar = json.getString("attentionAvatar");
                        String attentionId = json.getString("attentionId");

                        AttentionUser attentionUser1 = new AttentionUser();
                        attentionUser1.setAttentionName(mAttentionName);
                        attentionUser1.setAttentionLabel(attentionLabel);
                        attentionUser1.setAttentionImage(attentionImage);
                        attentionUser1.setAttentionAvatar(attentionAvatar);
                        attentionUser1.setAttentionId(attentionId);

                        mAttentionUsers.add(attentionUser1);

                    }
                    mFavorateAdapter = new FavorateAdapter();

                    mFavorateAdapter.setAttentionUsers(mAttentionUsers);
                    mLvFavorate.setAdapter(mFavorateAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            // !!!


//            try {
//                JSONObject jsonObject = new JSONObject(name);
//                JSONArray jsonArray = jsonObject.getJSONArray("mAttentionUsers");
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    JSONObject json = jsonArray.getJSONObject(i);
//                    mAttentionName = json.getString("attentionName");
//                    String attentionLabel = json.getString("attentionLabel");
//                    String attentionImage = json.getString("attentionImage");
//                    String attentionAvatar = json.getString("attentionAvatar");
//                    String attentionId = json.getString("attentionId");
//
//                    AttentionUser attentionUser1 = new AttentionUser();
//                    attentionUser1.setAttentionName(mAttentionName);
//                    attentionUser1.setAttentionLabel(attentionLabel);
//                    attentionUser1.setAttentionImage(attentionImage);
//                    attentionUser1.setAttentionAvatar(attentionAvatar);
//                    attentionUser1.setAttentionId(attentionId);
//
//                    mAttentionUsers.add(attentionUser1);
//
//                }
//                mFavorateAdapter = new FavorateAdapter();
//
//                mFavorateAdapter.setAttentionUsers(mAttentionUsers);
//                mLvFavorate.setAdapter(mFavorateAdapter);
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

        }


    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}

