package com.lu.beauty.designer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.lu.beauty.R;
import com.lu.beauty.base.BaseActivity;
import com.lu.beauty.bean.DesignerSecondBasicBean;
import com.lu.beauty.designer.threadactivity.DesignerItemBottomAdapter;
import com.lu.beauty.internet.HttpUtil;
import com.lu.beauty.internet.ResponseCallBack;
import com.lu.beauty.tools.CircleDrawable;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

/**
 * Created by GuoXuanYu on 16/12/3.
 */

public class DesignerItemActivity extends BaseActivity implements View.OnClickListener,SwipeBackActivityBase {

    private String id;
    private ImageView back;
    private SwipeBackActivityHelper swipeBackActivityHelper;
    private ViewPager mViewPager;
    private String[] picture;
    private DesignerItemPagerAdapter pagerAdapter;
    private LinearLayout ll;
    private ArrayList<PointImageView> pointImageViews;
    private ImageView circle;
    private TextView name;
    private TextView label;
    private TextView follow;
    private ExpandableTextView expandTV;
    private TextView concept;
    private TabLayout tab;
    private ViewPager viewPager;
    private String[] s ;

    // by 小玉
    public static final String INTENT_ID_KEY = "id"; // 跳转时传递过来的id

    @Override
    protected int getLayout() {
        return R.layout.activity_designer_item;
    }

    @Override
    protected void initViews() {
        back = bindView(R.id.activity_designer_item_back);
        mViewPager = bindView(R.id.activity_designer_item_vp);
        ll = bindView(R.id.activity_designer_item_ll);
        setClick(this,back);
        circle = bindView(R.id.activity_designer_item_circle);
        name = bindView(R.id.activity_designer_item_name);
        label = bindView(R.id.activity_designer_item_label);
        follow = bindView(R.id.activity_designer_item_follow);
        expandTV = bindView(R.id.expand_text_view);
        concept = bindView(R.id.activity_designer_item_concept);
        tab = bindView(R.id.activity_designer_item_tab);
        viewPager = bindView(R.id.activity_designer_item_viewpage);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra(INTENT_ID_KEY);
        Log.d("DesignerItemActivity", id);
        swipeBackActivityHelper = new SwipeBackActivityHelper(this);
        swipeBackActivityHelper.onActivityCreate();
        pagerAdapter = new DesignerItemPagerAdapter();
        setBasicMessage();
        DesignerItemBottomAdapter adapter = new DesignerItemBottomAdapter(getSupportFragmentManager(), s,id);
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);
        tab.setSelectedTabIndicatorColor(Color.WHITE);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_designer_item_back:
                onBackPressed();
                break;
        }
    }
    // 数据请求
    public void setBasicMessage(){
        HttpUtil.getDesignerSecondBasic(id, new ResponseCallBack<DesignerSecondBasicBean>() {
            @Override
            public void onResponse(DesignerSecondBasicBean designerSecondBasicBean) {
                picture = new String[designerSecondBasicBean.getData().getIntroduce_images().size()];
                for (int i = 0 ;i < designerSecondBasicBean.getData().getIntroduce_images().size();i++){

                    Log.d("DesignerItemActivity", designerSecondBasicBean.getData().getIntroduce_images().get(i));
                    picture[i] = designerSecondBasicBean.getData().getIntroduce_images().get(i);
                }
                pagerAdapter.setUrls(picture);
                mViewPager.setAdapter(pagerAdapter);
                initPoints();
                Glide.with(DesignerItemActivity.this).load(designerSecondBasicBean.getData().getAvatar_url()).
                        asBitmap().into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        CircleDrawable drawable = new CircleDrawable(resource);
                        circle.setImageDrawable(drawable);
                    }
                });
                name.setText(designerSecondBasicBean.getData().getName());
                label.setText(designerSecondBasicBean.getData().getLabel());
                follow.setText(designerSecondBasicBean.getData().getFollow_num()+"关注者");
                expandTV. setText(designerSecondBasicBean.getData().getDescription());
                concept.setText(designerSecondBasicBean.getData().getConcept());
                if (designerSecondBasicBean.getData().getArticle_num() != 0){

                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    // 初始化点
    public void initPoints(){
        pointImageViews = new ArrayList<>();
        for (int i = 0;i < pagerAdapter.getImageCount();i++){
            PointImageView point = new PointImageView(this);
            pointImageViews.add(point);
            LinearLayout.LayoutParams layoutParams
                    = new LinearLayout.LayoutParams(0,
                    ViewGroup.LayoutParams.MATCH_PARENT,1);
            ll.addView(point,layoutParams);
        }
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int current = position % pagerAdapter.getImageCount();
                for (PointImageView point : pointImageViews){
                    point.setSelected(false);
                }
                pointImageViews.get(current).setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    // 侧滑部分功能代码
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        swipeBackActivityHelper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && swipeBackActivityHelper != null)
            return swipeBackActivityHelper.findViewById(id);
        return v;
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return swipeBackActivityHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }
}
