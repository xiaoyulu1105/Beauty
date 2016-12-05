package com.lu.beauty.designer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseActivity;
import com.lu.beauty.bean.DesignerSecondBasicBean;
import com.lu.beauty.internet.HttpUtil;
import com.lu.beauty.internet.ResponseCallBack;

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
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        Log.d("DesignerItemActivity", id);
        swipeBackActivityHelper = new SwipeBackActivityHelper(this);
        swipeBackActivityHelper.onActivityCreate();
        pagerAdapter = new DesignerItemPagerAdapter();
        setBasicMessage();
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
