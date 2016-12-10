package com.lu.beauty.article;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseActivity;
import com.lu.beauty.designer.PointImageView;

import java.util.ArrayList;

/**
 * Created by XiaoyuLu on 16/12/6.
 * 将画报第二级的图片 的轮播显示 在该Activity里实现
 */

public class ArticleBannerActivity extends BaseActivity implements View.OnClickListener {
    private ViewPager mBannerViewPager;
    private LinearLayout mBannerPointsLl;
    private RelativeLayout mBannerRl;

    private ArticleBannerVPAdapter mBannerVPAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_article_images_banner;
    }

    @Override
    protected void initViews() {
        mBannerViewPager = (ViewPager) findViewById(R.id.article_banner_images_vp);
        mBannerPointsLl = (LinearLayout) findViewById(R.id.article_banner_points_ll);
        mBannerRl = (RelativeLayout) findViewById(R.id.article_banner_rl);
        mBannerRl.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        ArrayList<String> mImageUrlList = intent.getStringArrayListExtra("ArrayList");
        String getImageUrl = intent.getStringExtra("String");

        showBannerImage(mImageUrlList, getImageUrl);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.article_banner_rl:
                finish();

                break;
            default:
                Log.d("ArticleBannerActivity", "点错啦");
                break;
        }
    }

    /**
     * 在 showBannerImage 方法里 实现图片的 popUpWindow 里的 轮播图 的展示
     *
     * @param imageUrlList 图片的 网址的集合
     * @param getImageUrl 点击的图片的网址
     */
    public void showBannerImage(ArrayList<String> imageUrlList, String getImageUrl) {

        mBannerVPAdapter = new ArticleBannerVPAdapter();
        mBannerVPAdapter.setImageUrlList(imageUrlList);

        // 获取当前的图片的下标, 然后setCurrentItem(clickItem).
        int clickItem =  mBannerVPAdapter.getItemPosition(getImageUrl);
        // 下面两行代码 顺序不能变, 变了就又是从第一个图片开始轮播了.
        mBannerViewPager.setAdapter(mBannerVPAdapter);
        mBannerViewPager.setCurrentItem(clickItem);

        // 在点击事件 初始化菱形的点点 是因为点击后才知道图片的总数
        initBannerPoints(imageUrlList, getImageUrl);

    }

    /**
     * 设置轮播图 小菱形 的点点
     * @param imageUrlList
     * @param getImageUrl
     */
    private void initBannerPoints(ArrayList<String> imageUrlList, String getImageUrl) {
        // 需要先把 之前的点点 都删掉
        mBannerPointsLl.removeAllViews();

        final ArrayList<PointImageView> pointImageViews = new ArrayList<>();

        for (int i = 0; i < imageUrlList.size(); i++) {
            PointImageView pointImageView = new PointImageView(ArticleBannerActivity.this);
            pointImageViews.add(pointImageView);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
            mBannerPointsLl.addView(pointImageView, layoutParams);

        }

        // 为 ViewPager 添加监听
        mBannerViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                int current = position % mBannerVPAdapter.getImageUrlList().size();
                for (PointImageView pointImageView : pointImageViews) {
                    pointImageView.setSelected(false);
                }
                pointImageViews.get(current).setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
